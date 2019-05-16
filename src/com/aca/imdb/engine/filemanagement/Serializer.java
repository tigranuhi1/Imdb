package com.aca.imdb.engine.filemanagement;

import java.io.*;
import java.util.Base64;

class Serializer {
    /**
     * Read the object from Base64 string.
     */
    public static Object deserialize(String string) throws IOException,
            ClassNotFoundException {
        byte[] objectData = Base64.getDecoder().decode(string);
        ObjectInputStream objectInStream = new ObjectInputStream(new ByteArrayInputStream(objectData));
        Object object = objectInStream.readObject();
        objectInStream.close();
        return object;
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String serialize(Serializable object) throws IOException {
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
        objectOutStream.writeObject(object);
        objectOutStream.close();
        return Base64.getEncoder().encodeToString(byteOutStream.toByteArray());
    }
}
