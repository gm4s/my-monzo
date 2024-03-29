package io.freshdroid.mymonzo.home.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import io.freshdroid.mymonzo.core.extensions.bind
import io.freshdroid.mymonzo.home.R


class BottomNavigationItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val _iconImageView: ImageView by bind(R.id.iconImageView)

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_item_view, this, true)

        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationItemView, 0, 0)
            val drawable = a.getDrawable(R.styleable.BottomNavigationItemView_icon)
            if (drawable != null) {
                _iconImageView.setImageDrawable(drawable)
            }
            a.recycle()
        }
    }

    fun setActive(active: Boolean) {
        if (active) {
            _iconImageView.setColorFilter(
                ContextCompat.getColor(
                    context,
                    io.freshdroid.mymonzo.R.color.colorAccent
                ),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            _iconImageView.alpha = 1F
        } else {
            _iconImageView.setColorFilter(
                ContextCompat.getColor(
                    context,
                    io.freshdroid.mymonzo.R.color.colorPrimaryLight
                ),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            _iconImageView.alpha = 0.65F
        }
    }

}