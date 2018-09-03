package com.walker.core.util;

import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author Walker
 * @e-mail feitianwumu@163.com
 * @date on 2018/9/3
 * @summary pdf辅助类
 */
public class PdfUtils {

    /**
     * 初始化
     * @param webView webView
     */
    public static void init(WebView webView) {
        if (webView == null) {
            return;
        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }

    /**
     * 基于pdf.js加载本地pdf文件
     * @param webView webView
     * @param filePath 本地pdf文件路径
     */
    public static void loadFile(WebView webView, String filePath) {
        if (webView == null) {
            return;
        }

        //pdf.js放到本地
        webView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + filePath);
    }

    /**
     * 使用mozilla官方demo加载在线pdf
     * @param webView webView
     * @param pdfUrl 在线pdf地址
     */
    public static void loadFile2(WebView webView, String filePath) {
        if (webView == null) {
            return;
        }

        //使用mozilla官方demo加载在线pdf
        webView.loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + filePath);
    }

    /**
     * 基与pdf.js本地库自定义加载在线pdf
     * @param webView webView
     * @param pdfUrl 在线pdf地址
     */
    public static void loadUrl(WebView webView, String pdfUrl) {
        if (webView == null) {
            return;
        }
        //基与pdf.js本地库自定义预览UI
        webView.loadUrl("file:///android_asset/customViewer.html?" + pdfUrl);
    }
}
