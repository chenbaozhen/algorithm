package algorithm.myUtility.LeetCodeProcess;

import algorithm.myUtility.Display;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ExtractLeetCode {
    Map<Integer, Problem> map;
    public ExtractLeetCode() {
        map = new HashMap<>();
    }
    public static void main(String[] args) {
        ExtractLeetCode solution = new ExtractLeetCode();
        String str = "   <tr><td label=\"[object Object]\"></td><td label=\"[object Object]\">984</td><td value=\"String Without AAA or BBB\"\n" +
                "    label=\"[object Object]\"><div><a href=\"/problems/string-without-aaa-or-bbb\">String Without AAA or BBB</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "    <span class=\"badge badge-pill new-question-pill\">New</span></div></td><td label=\"[object Object]\"><a href=\"/articles/string-without-aaa-or-bbb\">\n" +
                "    <i class=\"fa fa-file-text\"></i></a></td><td value=\"31.059527200227855\" label=\"[object Object]\">31.1%</td><td value=\"[object Object]\" label=\"[object Object]\">\n" +
                "    <span class=\"label label-success round\">Easy</span></td><td value=\"0\" label=\"[object Object]\"><div class=\"progress\" style=\"width: 100px; margin-top: 5px;\n" +
                "    margin-bottom: 0px; height: 12px;\"><div class=\"progress-bar\n" +
                "                progress-bar-info\" style=\"width: 0px;\"></div></div></td></tr>";
        //System.out.println(solution.extractProblem(str));
        solution.addFiles("/Users/baozhenchen/Documents/coding/java/alvinchen/Practice/Practice/resource/google6month", "Google6");
        solution.addFiles("/Users/baozhenchen/Documents/coding/java/alvinchen/Practice/Practice/resource/facebook6month", "Facebook6");
        solution.addFiles("/Users/baozhenchen/Documents/coding/java/alvinchen/Practice/Practice/resource/treeTag", "tree");
        for (Map.Entry<Integer, Problem> entry : solution.map.entrySet()){
            if (entry.getValue().tags.contains("tree") && entry.getValue().tags.size() > 1)  System.out.println(entry.getValue());

        }
//        File file = new File("/Users/baozhenchen/Documents/coding/java/alvinchen/Practice/Practice/resource/google6month");
//
//        List<Problem> problems = solution.extractProblemsFromFile(file);
//
//
//        for (Problem p : problems) {
//            p.addTag("Google6");
//            solution.map.put(p.id, p);
//            System.out.println(p);
//        }
        //System.out.println(problems);



    }
    public void addFiles(String filepath, String tag){
        File file = new File(filepath);
        List<Problem> problems = extractProblemsFromFile(file);
        for (Problem p : problems) {
            map.computeIfAbsent(p.id, k -> p).addTag(tag);
        }
        return;
    }

    public List<Problem> extractProblemsFromFile(File file){
        try (BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(file))))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                return extractProblems(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Problem> extractProblems(String str) {
        List<Problem> problems = new ArrayList<>();
        int pt = 0;
        while (pt < str.length()) {
            int left = str.indexOf("<tr>", pt);
            if (left < 0) break;
            int right = str.indexOf("</tr>", left);
            if (right < 0) break;
            problems.add(extractProblem(str.substring(left, right)));
            pt = right + 4;
        }
        return problems;
    }
    public Problem extractProblem(String str) {
        int pt = 0;
        int id = 0;
        String name = "";
        String link = "";
        Double freq = -1.0;
        String difficulty = "";
        Double passRate = 0.0;

        while (pt < str.length()) {
            if (isNumber(str.charAt(pt)) && id == 0 && (pt > 0 && str.charAt(pt - 1) == '>')) {
                int fast = pt;
                while (fast + 1 < str.length() && isNumber(str.charAt(fast + 1)) ) {
                    fast++;
                }
                id = Integer.parseInt(str.substring(pt, fast + 1));
                //Display.myPrint(id);
                pt = ++fast;
            } else if (id > 0 &&name.length() == 0 && pt > 8 && str.substring(pt - 6, pt+1).equals("value=\"")) {
                int fast = pt;
                while (fast + 1 < str.length() && str.charAt(fast + 1) != '"') {
                    fast++;
                }
                //Display.myPrint(name);
                name = str.substring(pt + 1, fast + 1);
                pt = ++fast;
            } else if (link.length() == 0 && pt > 8 && str.substring(pt - 5, pt + 1).equals("href=\"") ) {
                int fast = pt;
                while (fast + 1 < str.length() && str.charAt(fast + 1) != '"') {
                    fast++;
                }
                link = str.substring(pt + 1, fast + 1);
                pt = ++fast;


            } else if (passRate == 0 && str.charAt(pt) == '%') {
                int slow = pt;
                while (slow - 1 >= 0 && isNumberOrDot(str.charAt(slow - 1))) {
                    slow--;
                }
                //Display.myPrint(str.substring(slow, pt));
                passRate = Double.parseDouble(str.substring(slow, pt));
                pt++;
            } else if (passRate > 0 && freq < 0 && str.substring(pt - 6, pt+1).equals("value=\"") && isNumber(str.charAt(pt + 1))) {
                int fast = pt;
                while (fast + 1 < str.length() && isNumberOrDot(str.charAt(fast + 1))) {
                    fast++;
                }
//                Display.myPrint(str.substring(pt + 1));
//                Display.myPrint(str.substring(pt + 1, fast + 1));
                freq = Double.parseDouble(str.substring(pt + 1, fast + 1));
                pt = fast + 1;
                break;
            }
            else {
                pt++;
            }
        }
        if (str.indexOf("Easy") >= 0) {
            difficulty = "Easy";
        } else if (str.indexOf("Medium") >= 0) {
            difficulty = "Medium";
        } else if (str.indexOf("Hard") >= 0) {
            difficulty = "Hard";
        }


//        Display.myPrint(id);
//        Display.myPrint(name);
//        Display.myPrint(link);
//        Display.myPrint(passRate);
//        Display.myPrint(freq);
//        Display.myPrint(difficulty);
        return new Problem(id, name, "https://leetcode.com" + link, freq, passRate, difficulty);
    }
    private boolean isNumber(char c) {
        return c >= '0' && c <='9';
    }
    private boolean isNumberOrDot(char c) {
        return (c >= '0' && c <='9') || c == '.' ;
    }

}

class Problem {
    int id;
    String name;
    String link;
    Double freq;
    String difficulty;
    Double passRate;
    Set<String> tags;

    public Problem(int id, String name, String link, Double freq, Double passRate, String difficulty) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.freq = freq;
        this.passRate = passRate;
        this.difficulty = difficulty;
        tags = new HashSet<>();
    }
    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public String toString() {
        return ""+ id +
                ", " + name +
                ", " + link +
                ", " + freq +
                ", " + difficulty +
                ", " + passRate +
                ", " + tags;
    }
//    public String toString() {
//        return "Problem{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", link='" + link + '\'' +
//                ", freq=" + freq +
//                ", difficulty='" + difficulty + '\'' +
//                ", passRate=" + passRate +
//                '}';
//    }
}
