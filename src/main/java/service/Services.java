package service;

import java.util.List;

/**
 * @author Sanjarbek Allayev, вс 10:35. 16.01.2022
 */
public class Services {
    public static void showAnyList(List  list){
        int i=1;
        for (Object obj:list) {
            System.out.println(i+") "+obj);
            i++;
        }
    }
}
