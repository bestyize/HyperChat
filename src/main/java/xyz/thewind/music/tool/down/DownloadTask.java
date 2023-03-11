package xyz.thewind.music.tool.down;

public class DownloadTask implements Runnable {

    private DownloadParams params;

    private DownloadListener listener;

    public DownloadTask(DownloadParams params, DownloadListener listener) {
        this.params = params;
        this.listener = listener;
    }

    @Override
    public void run() {
        DownloadHelper.download(params, listener);
    }
}
