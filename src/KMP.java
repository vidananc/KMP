import java.util.Scanner;

public class KMP
{
    private String s;
    private int[] next;
    private int[] preNext;//记录不考虑相同字符优化的next数组值
    public KMP(String s)
    {
        this.s = s;
        next = new int[s.length()];
        next[0] = -1;
        preNext = new int[s.length()];
        preNext[0] = -1;
        getNext();
    }
    private void getNext()
    {
        int k = -1;
        int j = 0;
        while(j < s.length() - 1)
        {
            if(k == -1 || s.charAt(k) == s.charAt(j)) preNext[++j] = ++k;
            else k = preNext[k];
        }
        j = 1;
        while(j < s.length())
        {
            k = preNext[j];
            while(k != -1 && s.charAt(j) == s.charAt(k)) k = preNext[k];
            next[j++] = k;
        }
    }
    public boolean contains(String l)
    {
        if(l.length() < s.length()) return false;
        int i = 0, j = 0;
        while(i < l.length() && j < s.length())
        {
            if(l.charAt(i) == s.charAt(j))
            {
                i++;
                j++;
            }
            else
            {
                if(next[j] == - 1)
                {
                    i++;
                    j = 0;
                }
                else j = next[j];
            }
        }
        return j == s.length();
    }
    public void printNext()
    {
        System.out.print("next: ");
        for(int e: next)
        {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        KMP b = new KMP("aabaaac");
        b.printNext();
        Scanner input = new Scanner(System.in);
        String line;
        while(!(line = input.nextLine()).equals("exit"))
        {
            System.out.println(b.contains(line));
        }
    }
}
