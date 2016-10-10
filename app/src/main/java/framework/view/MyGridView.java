package framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义GridView
 */
public class MyGridView extends GridView {

	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 自定义gridview的大小
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
     */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		　　一个MeasureSpec封装了父布局传递给子布局的布局要求，
// 每个MeasureSpec代表了一组宽度和高度的要求。一个MeasureSpec由大小和模式组成。
// 它有三种模式：UNSPECIFIED(未指定),父元素部队自元素施加任何束缚，
// 子元素可以得到任意想要的大小；EXACTLY(完全)，父元素决定自元素的确切大小，
// 子元素将被限定在给定的边界里而忽略它本身大小；AT_MOST(至多)，子元素至多达到指定大小的值。
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
