package Services;

import controller.LayoutCtr;
import model.Cinema;
import model.Cinplex;
import model.Layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataImport {
    public static List<Cinplex> loadCinplexFromFile(){
        List<Cinplex> cinplexes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/cinplex.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cinplex cinplex = new Cinplex();
                List<String> row_str = Arrays.asList(line.split(","));
                cinplex.setCinplexID(row_str.get(0).replaceAll("\\p{C}", ""));
                cinplex.setCineplexName(row_str.get(1).replaceAll("\\p{C}", ""));
                cinplex.setLocation(row_str.get(2).replaceAll("\\p{C}", ""));
                cinplexes.add(cinplex);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return cinplexes;
    }

    public static void loadCinemaFromFile(List<Cinplex> cinplexes){
        try(BufferedReader br = new BufferedReader(new FileReader("data/cinema.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cinema cinema = new Cinema();
                List<String> row_str = Arrays.asList(line.split(","));
                cinema.setCinemaCode(row_str.get(1).replaceAll("\\p{C}", ""));

                cinema.setClassOfCinema(Cinema.ClassOfCinema.valueOf(row_str.get(2).replaceAll("\\p{C}", "").toUpperCase()));
                cinema.setLayout(LayoutCtr.getLayoutById(row_str.get(3).replaceAll("\\p{C}", "")));
                for(Cinplex c : cinplexes){
                    if(c.getCinplexID().equals(row_str.get(0).replaceAll("\\p{C}", ""))){
                        c.addCinema(cinema);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
    }

    public static Layout importSingleLayout(String filepath, int index){
        ArrayList<Integer> s = new ArrayList<>();
        int row = 0;
        int col = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                row++;
                List<String> row_str = Arrays.asList(line.split(","));
                col = row_str.size() > col ? row_str.size():col;
                for(String cell : row_str) s.add(Integer.valueOf(cell.replaceAll("\\p{C}", "")));
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return new Layout(("layout_" + index), row, col, s);
    }

    public static List<Layout> importLayouts() {
        List<Layout> layouts = new ArrayList<>();
        String[] files = new File("data/layout").list();
        int count = 0;
        for(String f : files){
            System.out.println("data/layout/" + f);
            Layout l = importSingleLayout("data/layout/" + f, ++count);
            layouts.add(l);
        }
        return layouts;
    }


}
