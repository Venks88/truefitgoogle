package googlesetup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonFactoryClass {

    StringPlaceHolderClass spcObject = new StringPlaceHolderClass();

    public HttpURLConnection responseData() throws IOException {
        URL url = new URL(spcObject.sourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }
}
