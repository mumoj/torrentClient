package torrentclient;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class torrentFile {
    private String announce;
    private byte[] infoHash;
    private int length;

    public void TorrentFile(String announce, byte[] infoHash, int length) {
        this.announce = announce;
        this.infoHash = infoHash;
        this.length = length;
    }

    public String buildTrackerURL(byte[] peerID, int port, int downloaded) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(announce);

        builder.addParameter("info_hash", urlEncodeBytes(infoHash));
        builder.addParameter("peer_id", urlEncodeBytes(peerID));
        builder.addParameter("port", Integer.toString(port));
        builder.addParameter("uploaded", "0");
        builder.addParameter("downloaded", Integer.toString(downloaded));
        builder.addParameter("compact", "1");
        builder.addParameter("left", Integer.toString(length - downloaded));

        URI uri = builder.build();
        return uri.toString();
    }

    private String urlEncodeBytes(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%%%02X", b));
        }
        return sb.toString();
    }
}
