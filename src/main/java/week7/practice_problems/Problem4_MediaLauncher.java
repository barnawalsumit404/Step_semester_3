public class Problem4_MediaLauncher {
    interface Playable {
        String play();
        String play(int fromSecond);
        String pause();
    }

    abstract static class MediaFile {
        private static int counter = 1000;
        private final String fileId;

        MediaFile() {
            counter++;
            this.fileId = "MF-" + counter;
        }

        public abstract String getFormatInfo();

        public String getFileId() {
            return fileId;
        }
    }

    static class AudioFile extends MediaFile implements Playable {
        private final String title;

        public AudioFile(String title) {
            this.title = title;
        }

        @Override
        public String play() {
            return "Playing audio: " + title;
        }

        @Override
        public String play(int fromSecond) {
            return "Playing audio: " + title + " from 0:" + fromSecond;
        }

        @Override
        public String pause() {
            return "Paused audio: " + title;
        }

        @Override
        public String getFormatInfo() {
            return "Audio file, ID: " + getFileId();
        }
    }

    static class Podcast implements Playable {
        private final String showName;
        private final int episodeNumber;

        public Podcast(String showName, int episodeNumber) {
            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        @Override
        public String play() {
            return "Streaming episode " + episodeNumber + " of " + showName;
        }

        @Override
        public String play(int fromSecond) {
            return "Streaming episode " + episodeNumber + " of " + showName + " from 0:" + fromSecond;
        }

        @Override
        public String pause() {
            return "Paused podcast: " + showName;
        }
    }

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        Podcast p = new Podcast("Tech Talk", 12);
        System.out.println(p.play());

        Playable ref = a;
        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}
