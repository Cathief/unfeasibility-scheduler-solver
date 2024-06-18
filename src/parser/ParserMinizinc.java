package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserMinizinc {

    //Creamos un atributo parser
    private static ParserMinizinc instance;
    private  String dataFile;

    private ParserMinizinc(){

    }

    /**
     * Design pattern: Singleton.
     * @return la instancia del parser.
     */
    public static ParserMinizinc getInstance(){
        if (instance == null) {
            synchronized (ParserMinizinc.class) {
                if (instance == null) {
                    instance = new ParserMinizinc();
                }
            }
        }
        return instance;
    }

    public void setDataFile(String df) {
        this.dataFile = df;
    }

    public int parseJobNumber(){
        int nJobs = -1;
        String[] lines = dataFile.split("\n");
        for (String line: lines){
            if (line.startsWith("nJobs =")){
                String[] parts = line.split("=");
                return Integer.parseInt(parts[1].trim().replace(";", ""));
            }
        }
        return nJobs;
    }

    public int parseMachineNumber(){
        int nMachines = -1;
        String[] lines = dataFile.split("\n");
        for (String line: lines){
            if (line.startsWith("nMachines =")){
                String[] parts = line.split("=");
                return Integer.parseInt(parts[1].trim().replace(";", ""));
            }
        }
        return nMachines;
    }

    // Método para extraer las passive energy de las máquinas
    public List<Integer> parsePassiveEnergy() {
        List<Integer> peValues = new ArrayList<>();
        String[] lines = dataFile.split("\n");
        for (String line : lines) {
            if (line.startsWith("pe =")) {
                String[] parts = line.split("="); // Separa el nombre de la variable y su valor
                String values = parts[1].trim().replace(";", "").replace("[", "").replace("]", ""); // Limpia el string de valores
                String[] splitValues = values.split(","); // Separa los valores individuales
                for (String value : splitValues) {
                    peValues.add(Integer.parseInt(value.trim())); // Añade cada valor a la lista
                }
                break; // Termina el bucle una vez que encuentra y procesa 'pe'
            }
        }
        return peValues; // Retorna la lista de valores de 'pe'
    }

    // Método para leer el contenido del archivo y almacenarlo en una variable String
    public void read(String filePath) throws IOException {
        this.setDataFile(new String(Files.readAllBytes(Paths.get(filePath))));
    }

    public List<Integer> parseReadyTime() {
        List<Integer> peValues = new ArrayList<>();
        String[] lines = dataFile.split("\n");
        for (String line : lines) {
            if (line.startsWith("r =")) {
                String[] parts = line.split("="); // Separa el nombre de la variable y su valor
                String values = parts[1].trim().replace(";", "").replace("[", "").replace("]", ""); // Limpia el string de valores
                String[] splitValues = values.split(","); // Separa los valores individuales
                for (String value : splitValues) {
                    peValues.add(Integer.parseInt(value.trim())); // Añade cada valor a la lista
                }
                break; // Termina el bucle una vez que encuentra y procesa 'pe'
            }
        }
        return peValues; // Retorna la lista de valores de 'pe'
    }

    // Método para parsear el contenido de 'p' y almacenar cada bloque como una List<Integer>
    public List<List<Integer>> parseProcessingTime() {
        List<List<Integer>> pBlocks = new ArrayList<>();
        String[] lines = dataFile.split("\n");
        for (String line : lines) {
            if (line.startsWith("p =")) {
                // Eliminar 'p =', '[', y ']', y luego dividir por '|'
                String cleanLine = line.replace("p =", "").trim().replace("[", "")
                        .replace("]", "").replace(";","");
                String[] blocks = cleanLine.split("\\|");

                for (String block : blocks) {
                    List<Integer> blockValues = new ArrayList<>();
                    // Dividir cada bloque por ',' para obtener los valores individuales
                    String[] values = block.split(",");
                    for (String value : values) {
                        if (!value.trim().isEmpty()) { // Evitar cadenas vacías
                            blockValues.add(Integer.parseInt(value.trim()));
                        }
                    }
                    if (!blockValues.isEmpty()) {
                        pBlocks.add(blockValues);
                    }
                }
                break; // Romper el bucle después de procesar 'p'
            }
        }
        return pBlocks;
    }

    public List<List<Integer>> parseActiveEnergy() {
        List<List<Integer>> eBlocks = new ArrayList<>();
        String[] lines = dataFile.split("\n");
        for (String line : lines) {
            if (line.startsWith("e =")) {
                // Eliminar 'p =', '[', y ']', y luego dividir por '|'
                String cleanLine = line.replace("e =", "").trim().replace("[", "")
                        .replace("]", "").replace(";","");
                String[] blocks = cleanLine.split("\\|");

                for (String block : blocks) {
                    List<Integer> blockValues = new ArrayList<>();
                    // Dividir cada bloque por ',' para obtener los valores individuales
                    String[] values = block.split(",");
                    for (String value : values) {
                        if (!value.trim().isEmpty()) { // Evitar cadenas vacías
                            blockValues.add(Integer.parseInt(value.trim()));
                        }
                    }
                    if (!blockValues.isEmpty()) {
                        eBlocks.add(blockValues);
                    }
                }
                break; // Romper el bucle después de procesar 'p'
            }
        }
        return eBlocks;
    }
}
