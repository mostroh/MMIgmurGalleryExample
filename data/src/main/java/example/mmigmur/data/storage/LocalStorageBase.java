package example.mmigmur.data.storage;

/**
 * Created by Jorge Lavin Montoro on 17/11/2016.
 */

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class LocalStorageBase {

    Context context;

    String CR_FILE_NAME;

    public LocalStorageBase(Context context) {
        CR_FILE_NAME = this.getClass().getSimpleName();
        this.context = context;
    }

    protected void saveData(Map<String, String> dataMap) {
        String data = readFromFile();
        try {
            JSONObject jsonObj = data.isEmpty() ? new JSONObject() : new JSONObject(data);
            if (dataMap != null) {
                for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                    jsonObj.put(entry.getKey(), entry.getValue());
                }
            }
            data = jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!data.equals("")) {
            writeInFile(data);
        }
    }


    protected void saveData(String key, String value) {
        String data = readFromFile();
        try {
            JSONObject jsonObj = data.isEmpty() ? new JSONObject() : new JSONObject(data);
            jsonObj.put(key, value);
            data = jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!data.isEmpty()) {
            writeInFile(data);
        }
    }


    protected void saveData(String key, boolean value) {
        String data = readFromFile();
        try {
            JSONObject jsonObj = data.isEmpty() ? new JSONObject() : new JSONObject(data);
            jsonObj.put(key, value);
            data = jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!data.isEmpty()) {
            writeInFile(data);
        }
    }

    protected String loadData(String key) {
        String data = readFromFile();
        if (data.trim().length() > 0) {
            JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(data);
                if (jsonObj.has(key)) {
                    return jsonObj.getString(key);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    protected boolean loadBooleanData(String key) {
        String data = readFromFile();
        if (data.trim().length() > 0) {
            JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(data);
                if (jsonObj.has(key)) {
                    return jsonObj.getBoolean(key);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private void writeInFile(String element) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(CR_FILE_NAME, context.MODE_PRIVATE);
            outputStream.write(element.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        FileInputStream inputStream;
        try {
            if (context.getFileStreamPath(CR_FILE_NAME).exists()) {

                inputStream = context.openFileInput(CR_FILE_NAME);
                List<Byte> bytes = new ArrayList<>();
                byte[] buffer = new byte[1024];
                int n;
                while ((n = inputStream.read(buffer)) != -1) {
                    for (int i = 0; i < n; i++) {
                        bytes.add(buffer[i]);
                    }
                }
                inputStream.close();
                byte[] in = new byte[bytes.size()];
                for (int i = 0; i < bytes.size(); i++) {
                    in[i] = bytes.get(i).byteValue();
                }

                byte[] dec = in;


                return new String(dec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void clearData(){
        context.deleteFile(CR_FILE_NAME);
    }
}