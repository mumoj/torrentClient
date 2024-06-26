/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package torrentclient;

import com.dampcake.bencode.Bencode;
import com.dampcake.bencode.Type;
import com.frostwire.jlibtorrent.TorrentInfo;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;


public class App {
    public TorrentInfo getTorrentFile() throws Throwable {
        //String[] args = new String[]{"/home/jack/Documents/JavaProjects/TorrentClient/app/src/main/java/debian-12.5.0-amd64-netinst.iso.torrent"};
        String path = "D:\\WORKSTATION\\torrentClient\\app\\src\\main\\java\\debian-12.5.0-amd64-netinst.iso.torrent";
        byte[] torrentFile  = Files.readAllBytes(Paths.get(path));
        //System.out.println("Got Torrent:" + new String(torrentFile));

        Bencode bencode = new Bencode();
        Map<String, Object> dict = bencode.decode(torrentFile, Type.DICTIONARY);
        System.out.println("Got Dictionary" + dict.get("comment"));

        return null;
    }

    public static void main(String[] args) throws Throwable {
        new App().getTorrentFile() ;
    }
}
