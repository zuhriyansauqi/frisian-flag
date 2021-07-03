package com.zuhriyansauqi.frisianflag

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class PaginationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val oddStart = ContextCompat.getColor(context, R.color.odd_start)
    private val oddEnd = ContextCompat.getColor(context, R.color.odd_end)

    private val evenStart = ContextCompat.getColor(context, R.color.even_start)
    private val evenEnd = ContextCompat.getColor(context, R.color.even_end)

    private val whitePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private var listener: OnTouchListener? = null

    private var widthEach = 0f
    private var _numberOfItems = 0
    private var _selectedItem = -1
    private val startXs = mutableListOf<Float>()

    var numberOfItems
        get() = _numberOfItems
        set(value) {
            _numberOfItems = value
            invalidate()
        }

    var selectedItem
        get() = _selectedItem
        set(value) {
            _selectedItem = value
            invalidate()
        }

    init {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val tArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PaginationView, 0, 0)

        numberOfItems = tArray.getInt(R.styleable.PaginationView_totalPage, 0)
        selectedItem = tArray.getInt(R.styleable.PaginationView_selectedItem, -1)

        tArray.recycle()
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchEvent(event.x)
                performClick()
                true
            }
            else -> false
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (numberOfItems <= 0) return

        widthEach = width / _numberOfItems.toFloat()
        startXs.clear()

        var sum = 0.0f
        while (sum < w) {
            startXs.add(sum)
            sum += widthEach
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (numberOfItems <= 0) return

        startXs.forEachIndexed { index, start ->
            val end = (index + 1) * widthEach

            val paint = when {
                index + 1 == _selectedItem -> whitePaint
                index % 2 == 0 -> getOddBackground(start, end)
                else -> getEvenBackground(start, end)
            }

            canvas.drawRect(start, 0f, end, height.toFloat(), paint)

            val textPaint =
                if (index + 1 == _selectedItem) getSelectedTextPaint()
                else getTextPaint()

            canvas.drawText(
                (index + 1).toString(),
                ((index + 1) * (widthEach)) - (widthEach / 2f),
                (height / 2f) + getTextSize(height.toFloat()) / 2f,
                textPaint
            )
        }
    }

    private fun handleTouchEvent(x: Float) {
        val possibleSelectedItem = (x / widthEach).toInt()
        listener?.onItemTouched(possibleSelectedItem + 1)
    }

    private fun getSelectedTextPaint() =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, R.color.pagination_item_selected)
            textAlign = Paint.Align.CENTER
            textSize = getTextSize(height.toFloat())
            typeface = Typeface.DEFAULT_BOLD
        }

    private fun getTextPaint() =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, R.color.pagination_item)
            textAlign = Paint.Align.CENTER
            textSize = getTextSize(height.toFloat())
            typeface = Typeface.DEFAULT_BOLD
        }

    private fun getTextSize(height: Float) =
        if (widthEach < height)
            0.4f * widthEach
        else
            0.4f * height

    private fun getOddBackground(start: Float, end: Float) =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(
                start, 0f, end, 0f, oddStart, oddEnd, Shader.TileMode.CLAMP
            )
        }

    private fun getEvenBackground(start: Float, end: Float) =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(
                start, 0f, end, 0f, evenStart, evenEnd, Shader.TileMode.CLAMP
            )
        }

    fun registerListener(listener: OnTouchListener) {
        this.listener = listener
    }

    interface OnTouchListener {
        fun onItemTouched(page: Int)
    }
}
