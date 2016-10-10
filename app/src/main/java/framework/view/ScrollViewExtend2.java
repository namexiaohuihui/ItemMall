package framework.view;

/**
 * @项目名 : AidateR1V1
 * @包名:     com.aidate.travelassisant.tool
 * @类名: 	   ScrollowViewExtend
 * @创建者:  monian
 * @创建时间: 2015-7-20 下午2:45:32
 * @描述 TODO
 * @SVN版本: $Rev$
 * @更新人:     $Author$
 * @更新时间:  $Date$
 * @更新描述: TODO
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 能够兼容ViewPager的ScrollView
 * 
 * @Description: 解决了ViewPager在ScrollView中的滑动反弹问题，添加了控件划到顶部时置顶的方法
 * @Version V1.0
 */
public class ScrollViewExtend2 extends ScrollView {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;

	public ScrollViewExtend2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();
			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;
			if (xDistance > yDistance) {
				return false;
			}
		}
		return super.onInterceptTouchEvent(ev);
	}
}
