# FlexibleCore
A flexible library for Android

<dependency>
  <groupId>com.walker.drip</groupId>
  <artifactId>core</artifactId>
  <version>1.0.5</version>
  <type>pom</type>
</dependency>

implementation 'com.walker.drip:core:1.0.5'

#################################################################################################

#指纹识别的使用
1、清单文件中加入指纹识别权限
<!--指纹识别权限-->
<uses-permission android:name="android.permission.USE_FINGERPRINT" />
2、初始化指纹识别
private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
           //TODO 识别成功后的操作
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            ToastMaker.showShortToast("指纹识别失败，请重试！");
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            ToastMaker.showShortToast("指纹识别错误，等待几秒之后再重试");

        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };
    
     private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
    }
    
 3、调用识别和取消识别
 private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (mFingerprintCore.isAuthenticating()) {
                ToastMaker.showShortToast("指纹识别已经开启，长按指纹解锁键");
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            ToastMaker.showShortToast("此设备不支持指纹解锁");
        }
 }
    
 private void cancelFingerprintRecognition() {
        if (mFingerprintCore.isAuthenticating()) {
            mFingerprintCore.cancelAuthenticate();
        }
 }

#自动banner的使用
1、初始化banner
private void initBanner() {
       //设置布局（也可使用默认布局）
        //vpBannerActivity.setViewPagerLayout(R.layout.multi_layout_view_pager_home,R.id.vp_main,R.id.ll_main_dot);
        //  设置指示器位置
        vpBannerActivity.setIndicatorGravity(MultiBanner.CENTER);
        //  是否显示指示器
        vpBannerActivity.isShowIndicator(true);
        vpBannerActivity.setIndicatorColor(Color.parseColor("#eeeeee"), Color.parseColor("#bbbbbb"));
        //  设置图片切换时间间隔
        vpBannerActivity.setInterval(5000);
        //  设置是否无限循环
        vpBannerActivity.setCanLoop(true);
        //  设置是否自动轮播
        vpBannerActivity.setAutoPlay(true);
        //设置Page间间距
        vpBannerActivity.setPageMargin((int) DisplayUtils.dpToPx(getContext(), 10));
        //设置缓存的页面数量
        vpBannerActivity.setOffscreenPageLimit(10);
        vpBannerActivity.setPageTransformer(new ScaleInTransformer());
        //  设置页面点击事件
        vpBannerActivity.setOnPageClickListener(new MultiBanner.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                List<BannerBean> dataList = vpBannerActivity.getList();
                if (dataList.isEmpty()) {
                    return;
                }
                int pos = position + 1;
                if (pos < 0 || dataList.size() <= pos) {
                    return;
                }
                BannerBean banner = dataList.get(pos);
                startActivity(new Intent(getContext(), WebViewActivity.class)
                        .putExtra("URL", banner.getLocation())
                        .putExtra("TITLE", banner.getTitle())
                        .putExtra("PID", "")
                        .putExtra("BANNER", "banner")
                );
            }
        });
       //根据屏幕宽高适配布局大小（可选）
        //mBannerClipWidth = mDisplayMetrics.widthPixels - (int) (40 * mDisplayMetrics.density + 0.5f);//dp转化为px
        //mBannerClipHeight = (int) (mBannerClipWidth * CommonConstant.CLIP_BALANCE);//宽和高比例为(680，280)
        //vpBannerActivity.setContentLayoutParams(0, mBannerClipHeight);
        //  设置数据
        vpBannerActivity.setPages(lsad, new HolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }
2、创建banner具体展示布局（类于ImageView）
  private static class BannerViewHolder implements ViewHolder {
        private SimpleDraweeView imageView;

        @Override
        public View createView(Context context, int position) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.multi_item_banner_home, null);
            imageView = (SimpleDraweeView) view.findViewById(R.id.iv_pic);
            return view;
        }

        @Override
        public void onBind(Context context, Object data, int position, int size) {
            // 数据绑定
            if (data instanceof Integer) {
                imageView.setImageResource((Integer) data);
            } else if (data instanceof BannerBean) {
                imageView.setImageURI(((BannerBean) data).getImgUrl());
            }
        }

    }
3、启动或暂停banner
    private void startBanner() {
        try {
            if (vpBannerActivity != null) {
                vpBannerActivity.startLoop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void stopBanner() {
        try {
            if (vpBannerActivity != null) {
                vpBannerActivity.stopLoop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
