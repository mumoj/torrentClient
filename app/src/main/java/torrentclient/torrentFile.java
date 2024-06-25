package torrentclient;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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

    public String buildTrackerURL(byte[] peerID, int port) throws URISyntaxException {
        try {
            URI baseUri = new URI(announce);

            // Build the parameters
            Map<String, String> params = new HashMap<>();
            params.put("info_hash", new String(this.infoHash, StandardCharsets.ISO_8859_1));
            params.put("peer_id", new String(peerID, StandardCharsets.ISO_8859_1));
            params.put("port", Integer.toString(port));
            params.put("uploaded", "0");
            params.put("downloaded", "0");
            params.put("compact", "1");
            params.put("left", Integer.toString(length));

            String encodedParams = encodeParams(params);
            String fullUrl = baseUri + "?" + encodedParams;
            return fullUrl;
        }
        catch (Exception e){
            throw  new URISyntaxException("Error Building Torrent URL", e.getMessage());
        }
    }

    private String encodeParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry <String, String> entry: params.entrySet()){
            if (encodedParams.length() > 0) {encodedParams.append("&");}

            encodedParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.ISO_8859_1.name()));
            encodedParams.append("=");
            encodedParams.append(URLEncoder.encode(entry.getValue(), StandardCharsets.ISO_8859_1.name()));
        }
        return encodedParams.toString();
    }
}
