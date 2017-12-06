# YdPageState
android页面加载状态（内容页面，加载状态，空数据，无网络）<br>
# 使用
~~~
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  dependencies {
	        compile 'com.github.androidHarlan:YdPageState-master:v1.0.0'
	}
~~~~
# 使用方法
## 展示文本
  ~~~
 ydPageStateManager.showContent();  
 ~~~
 ydPageStateManager.showLoading(Indicator.PacmanIndicator);
             
  ## 展示空文本
  ~~~
 ydPageStateManager.showEmpty(getResources().getDrawable(R.mipmap.monkey_nodata), getString(R.string.ydPageState_empty_title), 
 getString(R.string.ydPageState_empty_details), new OnEmptyRetryListener() {
                            @Override
                            public void onEmptyRetry(View view) {
                                ydPageStateManager.showLoading(Indicator.BallBeatIndicator);
                            }
                        });
              
    
                //设置加载错误页显示
~~~
 ## 展示错误文本
 ~~~
ydPageStateManager.showError(getResources().getDrawable(R.mipmap.nointent),
                        getString(R.string.ydPageState_error_title), getString(R.string.ydPageState_error_details),
                        getString(R.string.ydPageState_retry), new OnErrorRetryListener() {
                            @Override
                            public void onErrorRetry(View view) {
                                ydPageStateManager.showLoading(Indicator.PacmanIndicator);
                            }
                        });
              
        }
~~~
