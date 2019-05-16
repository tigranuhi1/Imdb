package com.aca.imdb.engine.filemanagement;

import com.aca.imdb.engine.Readable;
import com.aca.imdb.engine.Writeable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository<K, V extends Serializable> implements Readable<K, V>, Writeable<K, V> {
    StringRepository repository;

    public Repository(String fileName) {
        repository = new StringRepository(fileName);
    }

    public Repository(Class<V> object) {
        repository = new StringRepository(object.getSimpleName() + ".txt");
    }

    @Override
    public V read(K key) {
        try {
            String value = repository.read(key.toString());
            if (value.equals("")) {
                return null;
            }
            return (V) Serializer.deserialize(value);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Something went wrong while reading from file.");
        }
    }

    @Override
    public List<V> readAll(K key) {
        try {
            List<String> values = repository.readAll(key.toString());
            if (values.size() == 0) {
                return null;
            }
            List<V> objectValues = new ArrayList<>();
            for (String value : values) {
                objectValues.add((V) Serializer.deserialize(value));
            }
            return objectValues;
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Something went wrong while reading from file.");
        }
    }

    @Override
    public void write(K key, V value) {
        try {
            repository.write(key.toString(), Serializer.serialize(value));
        } catch (IOException ex) {
            throw new RuntimeException("Something went wrong with writeUnique to file.");
        }
    }

    @Override
    public void writeUnique(K key, V value) {
        try {
            repository.writeUnique(key.toString(), Serializer.serialize(value));
        } catch (IOException ex) {
            throw new RuntimeException("Something went wrong with writeUnique to file.");
        }
    }

    private class StringRepository implements Readable<String, String>, Writeable<String, String> {
        private File file;
        private BufferedReader reader;
        private BufferedWriter writer;

        StringRepository(String fileName) {
            try {
                file = new File(fileName);
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException("Something went wrong with file.");
            }
        }

        @Override
        public List<String> readAll(String key) {
            List<String> values = new ArrayList<>();
            String value = "";
            String line;
            try {
                FileReader fileReader = new FileReader(file);
                reader = new BufferedReader(fileReader);

                if (reader.ready()) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(key)) {
                            values.add(line.substring(key.length() + 2));
                            //value += line.substring(key.length() + 2) + "\n";
                        }
                    }
                }
                reader.close();
                return values;

            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }

        @Override
        public void writeUnique(String key, String value) {
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
                if (!read(key).equals("")) {
                    writer.close();
                    return;
                }

                writer.write(key + ": " + value);
                writer.newLine();
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }

        @Override
        public void write(String key, String value) {
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(key + ": " + value);
                writer.newLine();
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }

        public String read(String key) {
            String value = "";
            String line;
            try {
                FileReader fileReader = new FileReader(file);
                reader = new BufferedReader(fileReader);

                if (reader.ready()) {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(key)) {
                            value = line.substring(key.length() + 2);
                            break;
                        }
                    }
                }
                reader.close();
                return value;

            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
    }
}
