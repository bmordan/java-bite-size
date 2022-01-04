import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class FileSystem {
    Scanner sc = null;
    FileSystem() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        LinkedList<int[]> ll = new LinkedList<>();
        
        ll.add(new int[]{12,13,14});
        ll.add(new int[]{22,23,24});
        ll.add(new int[]{100,101,102});

        Iterator<int[]> ii = ll.iterator();
        
        while(ii.hasNext()) {
            for(int jobNo : ii.next()) {
                q.offer(jobNo);
            }
        }

        while(q.size() > 0) {
            System.out.printf("Job No. %d\n", q.poll());
        }

        File dir = new File("markdown");
        File files[] = dir.listFiles();
        for (File file : files) {
            System.out.printf("%s (%s)\n", file.getAbsolutePath(), file.getFreeSpace());
            try {
                sc = new Scanner(file);
                String md;
                StringBuffer sb = new StringBuffer();
                while(sc.hasNextLine()) {
                    md = sc.nextLine();
                    sb.append(md + "\n");
                }
                String content = convertToHtml(sb.toString());
                createFile(file.getName(), content);
            } catch(FileNotFoundException err) {
                System.out.println(err.getMessage());
            }
        }
    }
    private String convertToHtml (String md) {
        Parser parser = Parser.builder().build();
        Node doc = parser.parse(md);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(doc);
    }
    private void createFile (String filename, String content) {
        try {
            FileWriter fw = new FileWriter("dist/" + filename.replace(".md", ".html"));
            fw.write(content);
            fw.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
    public static void main(String[] args) {
        new FileSystem();
    } 
}
