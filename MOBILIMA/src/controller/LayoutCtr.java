package controller;

import model.Layout;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LayoutCtr {
    public static ArrayList<Layout> layouts = new ArrayList<Layout>();

    public static void importSingleLayout(String filepath){
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
        Layout layout = new Layout(("layout_" + (layouts.size()+1)), row, col, s);
        layouts.add(layout);
    }
    public static void importLayout() {
        String[] files = new File("data/layout").list();
        for(String f : files){
            System.out.println("data/layout/" + f);
            importSingleLayout("data/layout/" + f);
        }
    }

    public static Layout getLayoutById(String id){
        for(Layout l : layouts){
            if(l.getLayout_id().equals(id)) return l;
        }
        return null;
    }
}
