package asd.asd.asdasd;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ListView;

public class BottomWebView extends WebView {
    public BottomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    ListView s;
    public void scrollToBottom() {
        scrollTo(0, computeVerticalScrollRange());
    }
}
