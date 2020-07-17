import java.io.*;
import java.util.Scanner;
import java.util.zip.*;

public class archivator {

    public static void main(String[] args) {

        String filename = "/home/ddoniyor/javaFile.txt";

        System.out.print("1.make ZIP file \n2.make UnZIP file");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        /*while(x!=0){
            if(x!=1 && x!=2){
                System.out.println("You choose wrong number!");
                break;
            }else{continue;}

        }*/
        switch (x){
            case 1:
                archiveFile.makeItZip(filename);
            case 2:
                archiveFile.makeItUnZip();

        }

    }
}
class archiveFile{
    static void makeItZip(String filename){
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/home/ddoniyor/outFile.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry("javaFile.txt");
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }

    static void makeItUnZip(){
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("/home/ddoniyor/outFile.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);

                // распаковка
                FileOutputStream fout = new FileOutputStream("/home/ddoniyor/new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}