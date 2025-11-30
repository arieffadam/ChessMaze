package com.example.chessmaze

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.graphics.BitmapFactory
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class GameView(context: Context) : View(context) {

    private val cols = 6
    private val rows = 8
    private var cellSize = 0f
    
    // Game State
    private var currentLevel = 1 // 1 = Rook, 2 = Knight
    private var playerCol = 0
    private var playerRow = 0
    private var starCol = 5
    private var starRow = 7

    // Define Walls (var so we can change it for new levels)
    private var walls = listOf(
        Point(2, 2), Point(2, 3), Point(2, 4), 
        Point(4, 5), Point(3, 5)
    )

    // Bitmaps - Initialized in onSizeChanged
    private lateinit var rookBitmap: Bitmap
    private lateinit var knightBitmap: Bitmap
    private lateinit var starBitmap: Bitmap
    private lateinit var wallBitmap: Bitmap

    // Paints
    private val boardPaint = Paint().apply {
        color = Color.DKGRAY
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cellSize = w.toFloat() / cols

        // Load Bitmaps here to avoid crashes
        rookBitmap = BitmapFactory.decodeResource(resources, R.drawable.rook)
        knightBitmap = BitmapFactory.decodeResource(resources, R.drawable.knight)
        starBitmap = BitmapFactory.decodeResource(resources, R.drawable.star)
        wallBitmap = BitmapFactory.decodeResource(resources, R.drawable.wall)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawGrid(canvas)
        drawWallsAndStar(canvas)
        drawPlayer(canvas)
    }

    private fun drawGrid(canvas: Canvas) {
        for (c in 0..cols) {
            canvas.drawLine(c * cellSize, 0f, c * cellSize, rows * cellSize, boardPaint)
        }
        for (r in 0..rows) {
            canvas.drawLine(0f, r * cellSize, cols * cellSize, r * cellSize, boardPaint)
        }
    }

    private fun drawWallsAndStar(canvas: Canvas) {
        val dstRect = Rect()
        val size = cellSize.toInt()

        // Draw Walls
        for (wall in walls) {
            val wx = (wall.x * cellSize).toInt()
            val wy = (wall.y * cellSize).toInt()
            dstRect.set(wx, wy, wx + size, wy + size)
            canvas.drawBitmap(wallBitmap, null, dstRect, null)
        }

        // Draw Star
        val sx = (starCol * cellSize).toInt()
        val sy = (starRow * cellSize).toInt()
        val padding = (size * 0.15).toInt()
        dstRect.set(sx + padding, sy + padding, sx + size - padding, sy + size - padding)
        canvas.drawBitmap(starBitmap, null, dstRect, null)
    }

    private fun drawPlayer(canvas: Canvas) {
        val px = (playerCol * cellSize).toInt()
        val py = (playerRow * cellSize).toInt()
        val size = cellSize.toInt()

        val dstRect = Rect()
        val padding = (size * 0.1).toInt()
        dstRect.set(px + padding, py + padding, px + size - padding, py + size - padding)

        if (currentLevel == 1) {
            canvas.drawBitmap(rookBitmap, null, dstRect, null)
        } else {
            canvas.drawBitmap(knightBitmap, null, dstRect, null)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val targetCol = (event.x / cellSize).toInt()
            val targetRow = (event.y / cellSize).toInt()

            if (targetCol >= cols || targetRow >= rows) return true

            for (wall in walls) {
                if (wall.x == targetCol && wall.y == targetRow) {
                     Toast.makeText(context, "Blocked by Wall!", Toast.LENGTH_SHORT).show()
                     return true
                }
            }

            var moveValid = false

            if (currentLevel == 1) {
                val isStraightLine = (targetCol == playerCol) || (targetRow == playerRow)
                if (isStraightLine && !isPathBlocked(targetCol, targetRow)) {
                    moveValid = true
                }
            }
            else if (currentLevel == 2) {
                val dx = abs(targetCol - playerCol)
                val dy = abs(targetRow - playerRow)
                if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
                    moveValid = true
                }
            }

            if (moveValid) {
                playerCol = targetCol
                playerRow = targetRow
                checkWin()
                invalidate()
            }
        }
        return true
    }

    private fun isPathBlocked(targetCol: Int, targetRow: Int): Boolean {
        for (wall in walls) {
            if (playerCol == targetCol && wall.x == playerCol) {
                if (wall.y > min(playerRow, targetRow) && wall.y < max(playerRow, targetRow)) {
                    return true
                }
            }
            if (playerRow == targetRow && wall.y == playerRow) {
                if (wall.x > min(playerCol, targetCol) && wall.x < max(playerCol, targetCol)) {
                    return true
                }
            }
        }
        return false
    }

    private fun checkWin() {
        if (playerCol == starCol && playerRow == starRow) {
            if (currentLevel == 1) {
                Toast.makeText(context, "Nice! Level 2: The Knight!", Toast.LENGTH_LONG).show()
                currentLevel = 2
                
                playerCol = 0
                playerRow = 0
                
                starCol = 0
                starRow = 7
                walls = listOf(Point(1, 2), Point(2, 2), Point(3, 2), Point(3, 3)) 
                
            } else {
                Toast.makeText(context, "YOU WON THE GAME!", Toast.LENGTH_LONG).show()
                currentLevel = 1
                playerCol = 0
                playerRow = 0
                starCol = 5
                starRow = 7
                walls = listOf(Point(2, 2), Point(2, 3), Point(2, 4), Point(4, 5), Point(3, 5))
            }
        }
    }
}