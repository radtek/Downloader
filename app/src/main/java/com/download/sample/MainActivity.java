package com.download.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.download.library.DownloadException;
import com.download.library.DownloadImpl;
import com.download.library.DownloadListenerAdapter;
import com.download.library.DownloadTask;
import com.download.library.Downloader;
import com.download.library.Extra;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;


/**
 * @author ringle-android
 * @date 19-2-12
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {
	private RecyclerView mRecyclerView;
	private ArrayList<DownloadBean> mDownloadTasks = new ArrayList<DownloadBean>();
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_native_download);
		createDatasource();
		mRecyclerView = this.findViewById(R.id.download_recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(new NativeDownloadAdapter());
		FileDownloader.setup(this.getApplicationContext());
		this.findViewById(R.id.pauseAllBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadImpl.getInstance().pauseAll();
			}
		});
		this.findViewById(R.id.resumeAllBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadImpl.getInstance().resumeAll();
			}
		});
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                File file = DownloadImpl.getInstance()
		                .with(getApplicationContext())
		                .target(new File(getCacheDir(),"t01a16bcd9acd07d029.png"))
		                .url("http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086\", \"http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086")
		                .setDownloadListenerAdapter(new DownloadListenerAdapter() {
                    @Override
                    public void onProgress(String url, long downloaded, long length, long usedTime) {
                        super.onProgress(url, downloaded, length, usedTime);
                        Log.i(TAG, " downloaded:" + downloaded);
                    }

                    @Override
                    public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
                        Log.i(TAG, "downloaded onResult isSuccess:" + (throwable == null) + " url:" + url + " Thread:" + Thread.currentThread().getName() + " uri:" + path.toString());

                        return super.onResult(throwable, path, url, extra);
                    }
                }).get();
                Log.i(TAG, " download success:" + ((File) file).length());
            }
        }).start();*/
        /*DownloadImpl.getInstance()
                .with(getApplicationContext())
                .setEnableIndicator(true)
                .url("http://shouji.360tpcdn.com/170918/f7aa8587561e4031553316ada312ab38/com.tencent.qqlive_13049.apk")
                .enqueue(new DownloadListenerAdapter() {
                    @Override
                    public void onProgress(String url, long downloaded, long length, long usedTime) {
                        super.onProgress(url, downloaded, length, usedTime);
                        Log.i(TAG, " progress:" + downloaded + " url:" + url);
                    }

                    @Override
                    public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
                        Log.i(TAG, " path:" + path + " url:" + url + " length:" + new File(path.getPath()).length());
                        return super.onResult(throwable, path, url, extra);
                    }
                });*/

		/*DownloadImpl.getInstance()
				.with(getApplicationContext())
				.target(new File(this.getExternalCacheDir(), "com.ss.android.article.news_636.apk"))
				.setUniquePath(false)
				.setForceDownload(true)
				.url("http://shouji.360tpcdn.com/170918/93d1695d87df5a0c0002058afc0361f1/com.ss.android.article.news_636.apk")
				.enqueue(new DownloadListenerAdapter() {
					@Override
					public void onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, Extra extra) {
						super.onStart(url, userAgent, contentDisposition, mimetype, contentLength, extra);
					}

					@Override
					public void onProgress(String url, long downloaded, long length, long usedTime) {
						super.onProgress(url, downloaded, length, usedTime);
						Log.i(TAG, " progress:" + downloaded + " url:" + url);
					}

					@Override
					public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
						Log.i(TAG, " path:" + path + " url:" + url + " length:" + new File(path.getPath()).length());
						return super.onResult(throwable, path, url, extra);
					}
				});*/
//		run2();
	}

	private void run3() {
		final long begin1 = SystemClock.elapsedRealtime();
		FileDownloader.getImpl()
				.create("http://shouji.360tpcdn.com/170918/93d1695d87df5a0c0002058afc0361f1/com.ss.android.article.news_636.apk")
				.setPath(this.getCacheDir().getAbsolutePath() + "/" + "test.apk")
				.setListener(new FileDownloadListener() {
					@Override
					protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

					}

					@Override
					protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
						Log.i(TAG, "FileDownloader progress:" + soFarBytes);
					}

					@Override
					protected void completed(BaseDownloadTask task) {
						Log.i(TAG, " FileDownloader time:" + (SystemClock.elapsedRealtime() - begin1)
								+ " length:" + new File(task.getPath()).length()
								+ "  path:" + task.getPath());
						new File(task.getPath()).delete();
//						run2();
					}

					@Override
					protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

					}

					@Override
					protected void error(BaseDownloadTask task, Throwable e) {
						e.printStackTrace();
					}

					@Override
					protected void warn(BaseDownloadTask task) {

					}
				}).start();
	}

	private void run2() {
		final long begin = SystemClock.elapsedRealtime();
		DownloadImpl.getInstance()
				.with(getApplicationContext())
				.url("http://shouji.360tpcdn.com/170918/93d1695d87df5a0c0002058afc0361f1/com.ss.android.article.news_636.apk")
				.enqueue(new DownloadListenerAdapter() {
					@Override
					public void onProgress(String url, long downloaded, long length, long usedTime) {
						super.onProgress(url, downloaded, length, usedTime);
						Log.i(TAG, " progress:" + downloaded + " url:" + url);
					}

					@Override
					public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
						Log.i(TAG, " path:" + path + " url:" + url + " length:" + new File(path.getPath()).length());
						Log.i(TAG, " DownloadImpl time:" + (SystemClock.elapsedRealtime() - begin) + " length:" + new File(path.getPath()).length());
						run3();
						return super.onResult(throwable, path, url, extra);
					}
				});
	}

	private class NativeDownloadAdapter extends RecyclerView.Adapter<NativeDownloadViewHolder> {
		@NonNull
		@Override
		public NativeDownloadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_download, viewGroup, false);
			return new NativeDownloadViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull final NativeDownloadViewHolder nativeDownloadViewHolder, int i) {
			final DownloadBean downloadBean = mDownloadTasks.get(i);
			Picasso.get().load(downloadBean.imageUrl)
					.resize(100, 100)
					.centerCrop().
					transform(new RoundTransform(MainActivity.this.getApplicationContext()))
					.into(nativeDownloadViewHolder.mIconIv);
			if (downloadBean.getStatus() == DownloadTask.STATUS_NEW) {
				nativeDownloadViewHolder.mStatusButton.setText("开始");
			} else if (downloadBean.getStatus() == DownloadTask.STATUS_PENDDING || downloadBean.getStatus() == DownloadTask.STATUS_DOWNLOADING) {
				nativeDownloadViewHolder.mStatusButton.setText("暂停");
			} else {
				nativeDownloadViewHolder.mStatusButton.setText("已完成");
				nativeDownloadViewHolder.mStatusButton.setEnabled(false);
			}
			nativeDownloadViewHolder.mStatusButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (downloadBean.getStatus() == DownloadTask.STATUS_NEW) {
						DownloadImpl.getInstance().enqueue(downloadBean);
						nativeDownloadViewHolder.mStatusButton.setText("暂停");
					} else if (downloadBean.getStatus() == DownloadTask.STATUS_PENDDING || downloadBean.getStatus() == DownloadTask.STATUS_DOWNLOADING) {
						DownloadImpl.getInstance().pause(downloadBean.getUrl());
						nativeDownloadViewHolder.mStatusButton.setText("继续");
					} else if (downloadBean.getStatus() == DownloadTask.STATUS_PAUSED) {
						DownloadImpl.getInstance().resume(downloadBean.getUrl());
						nativeDownloadViewHolder.mStatusButton.setText("暂停");
					} else {
						nativeDownloadViewHolder.mStatusButton.setEnabled(false);
						nativeDownloadViewHolder.mStatusButton.setText("已完成");
					}
				}
			});
			downloadBean.setDownloadListenerAdapter(new DownloadListenerAdapter() {
				@Override
				public void onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, Extra extra) {
					nativeDownloadViewHolder.mStatusButton.setText("暂停");
					nativeDownloadViewHolder.mStatusButton.setEnabled(true);
				}

				@MainThread //回调到主线程，添加该注释
				@Override
				public void onProgress(String url, long downloaded, long length, long usedTime) {
					int mProgress = (int) ((downloaded) / Float.valueOf(length) * 100);
					Log.i(TAG, "onProgress:" + mProgress + " url:" + url + " Thread:" + Thread.currentThread().getName());
					nativeDownloadViewHolder.mProgressBar.setProgress(mProgress);
					if (length <= 0) {
						nativeDownloadViewHolder.mCurrentProgress.setText("当前进度,已下载:" + byte2FitMemorySize(downloaded) + " 耗时:" + ((downloadBean.getUsedTime()) / 1000) + "s");
					} else {
						nativeDownloadViewHolder.mCurrentProgress.setText("当前进度" + byte2FitMemorySize(downloaded) + "/" + byte2FitMemorySize(length) + " 耗时:" + ((downloadBean.getUsedTime()) / 1000) + "s");

					}
				}

				@Override
				public boolean onResult(Throwable throwable, Uri uri, String url, Extra extra) {
					Log.i(TAG, "onResult isSuccess:" + (throwable == null) + " url:" + url + " Thread:" + Thread.currentThread().getName() + " uri:" + uri.toString());
					nativeDownloadViewHolder.mStatusButton.setEnabled(false);
					if (throwable == null) {
						nativeDownloadViewHolder.mStatusButton.setText("已完成");
					} else if (throwable instanceof DownloadException) {
						DownloadException downloadException = (DownloadException) throwable;
						if (downloadException.getCode() == Downloader.ERROR_USER_PAUSE) {
							nativeDownloadViewHolder.mStatusButton.setText("继续");
							nativeDownloadViewHolder.mStatusButton.setEnabled(true);
						} else {
							nativeDownloadViewHolder.mStatusButton.setText("出错");
						}
					}
					return super.onResult(throwable, uri, url, extra);
				}
			});

		}

		@Override
		public int getItemCount() {
			return mDownloadTasks.size();
		}
	}

	private static String byte2FitMemorySize(final long byteNum) {
		if (byteNum < 0) {
			return "";
		} else if (byteNum < 1024) {
			return String.format(Locale.getDefault(), "%.1fB", (double) byteNum);
		} else if (byteNum < 1048576) {
			return String.format(Locale.getDefault(), "%.1fKB", (double) byteNum / 1024);
		} else if (byteNum < 1073741824) {
			return String.format(Locale.getDefault(), "%.1fMB", (double) byteNum / 1048576);
		} else {
			return String.format(Locale.getDefault(), "%.1fGB", (double) byteNum / 1073741824);
		}
	}

	private class NativeDownloadViewHolder extends RecyclerView.ViewHolder {
		ProgressBar mProgressBar;
		Button mStatusButton;
		ImageView mIconIv;
		private final TextView mCurrentProgress;

		public NativeDownloadViewHolder(@NonNull View itemView) {
			super(itemView);
			mIconIv = itemView.findViewById(R.id.icon_iv);
			mStatusButton = itemView.findViewById(R.id.start_button);
			mProgressBar = itemView.findViewById(R.id.progressBar);
			mProgressBar.setMax(100);
			mCurrentProgress = itemView.findViewById(R.id.current_progress);
		}
	}

	public static class DownloadBean extends DownloadTask {
		public String title;
		public String imageUrl;

		public DownloadBean(String title, String imageUrl, String url) {
			this.title = title;
			this.imageUrl = imageUrl;
			this.mUrl = url;
		}
	}

	public static class RoundTransform implements com.squareup.picasso.Transformation {

		private Context mContext;

		public RoundTransform(Context context) {
			mContext = context;
		}

		@Override
		public Bitmap transform(Bitmap source) {

			int widthLight = source.getWidth();
			int heightLight = source.getHeight();
			int radius = dp2px(mContext, 8); // 圆角半径

			Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

			Canvas canvas = new Canvas(output);
			Paint paintColor = new Paint();
			paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

			RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

			canvas.drawRoundRect(rectF, radius, radius, paintColor);
//        canvas.drawRoundRect(rectF, widthLight / 5, heightLight / 5, paintColor);

			Paint paintImage = new Paint();
			paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
			canvas.drawBitmap(source, 0, 0, paintImage);
			source.recycle();
			return output;
		}

		@Override
		public String key() {
			return "roundcorner";
		}

	}

	public void createDatasource() {
		DownloadBean downloadBean = new DownloadBean("QQ", "http://p18.qhimg.com/dr/72__/t0111cb71dabfd83b21.png", "https://d71329e5c0be6cdc2b46d0df2b4bd841.dd.cdntips.com/imtt.dd.qq.com/16891/apk/06AB1F5B0A51BEFD859B2B0D6B9ED9D9.apk?mkey=5d47b9f223f7bc0d&f=1806&fsname=com.tencent.mobileqq_8.1.0_1232.apk&csr=1bbd&cip=35.247.154.248&proto=https");
		downloadBean.setContext(this.getApplicationContext());
		mDownloadTasks.add(downloadBean);
		downloadBean = new DownloadBean("支付宝", "http://p18.qhimg.com/dr/72__/t01a16bcd9acd07d029.png", "http://shouji.360tpcdn.com/170919/e7f5386759129f378731520a4c953213/com.eg.android.AlipayGphone_115.apk");
		downloadBean.setContext(this.getApplicationContext());
		mDownloadTasks.add(downloadBean);

		downloadBean = new DownloadBean("UC", "http://p19.qhimg.com/dr/72__/t01195d02b486ef8ebe.png", "http://shouji.360tpcdn.com/170919/9f1c0f93a445d7d788519f38fdb3de77/com.UCMobile_704.apk");
		downloadBean.setContext(this.getApplicationContext());
		mDownloadTasks.add(downloadBean);

		downloadBean = new DownloadBean("腾讯视频", "http://p18.qhimg.com/dr/72__/t01ed14e0ab1a768377.png", "http://shouji.360tpcdn.com/170918/f7aa8587561e4031553316ada312ab38/com.tencent.qqlive_13049.apk");
		downloadBean.setContext(this.getApplicationContext());
		mDownloadTasks.add(downloadBean);

		downloadBean = new DownloadBean("头条", "http://p15.qhimg.com/dr/72__/t013d31024ae54d9c35.png", "http://shouji.360tpcdn.com/170918/93d1695d87df5a0c0002058afc0361f1/com.ss.android.article.news_636.apk");
		downloadBean.setContext(this.getApplicationContext());
		downloadBean.setEnableIndicator(false);
		mDownloadTasks.add(downloadBean);

		downloadBean = new DownloadBean("淘宝", "http://p15.qhimg.com/dr/72__/t011cd515c7c9390202.png", "http://shouji.360tpcdn.com/170901/ec1eaad9d0108b30d8bd602da9954bb7/com.taobao.taobao_161.apk");
		downloadBean.setContext(this.getApplicationContext());
		mDownloadTasks.add(downloadBean);

		//http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086

		downloadBean = new DownloadBean("分块传输，图片", "http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086", "http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086");
		downloadBean.setContext(this.getApplicationContext());
		downloadBean.setAutoOpen(true);
		mDownloadTasks.add(downloadBean);


		downloadBean = new DownloadBean("天天跑酷", "http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086", "http://183.232.175.155/imtt.dd.qq.com/16891/83B450E67953CC7C4A3ECFBF70BC276D.apk?mkey=5c69a52c78e505fd&f=1026&fsname=com.tencent.pao_1.0.62.0_162.apk&csr=97c2&cip=120.229.35.8&proto=http");
		downloadBean.setContext(this.getApplicationContext());
		downloadBean.setAutoOpen(true);
		mDownloadTasks.add(downloadBean);

		downloadBean = new DownloadBean("应用宝", "http://www.httpwatch.com/httpgallery/chunked/chunkedimage.aspx?0.04400023248109086", "http://183.235.254.101/cache/imtt.dd.qq.com/16891/channel_73570706_1000047_1550372800316.apk?hsr=5848&cip=120.229.35.8&proto=http&ich_args2=29-17235801007468_b6001a85eb3b78e73d317dae40d3becc_10004302_9c89612cd3c2f8d09539518939a83798_65e2b3e5968aa7f2bea9fdae95ebc635 ");
		downloadBean.setContext(this.getApplicationContext());
		downloadBean.setAutoOpen(true);
		mDownloadTasks.add(downloadBean);
	}

	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}

