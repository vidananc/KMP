import java.util.Scanner;

public class BMP
{
    private String s;
    private int[] next;
    private int[] preNext;//记录不考虑相同字符优化的next数组值
    public BMP(String s)
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
        for(int i = 1; i < s.length(); i++)
        {
            if(preNext[i - 1] == -1) preNext[i] = 0;
            else
            {
                if(s.charAt(preNext[i - 1]) == s.charAt(i - 1)) preNext[i] = preNext[i - 1] + 1;
                else preNext[i] = 0;
            }
            if(preNext[i] != -1)
            {
                if(s.charAt(i) == s.charAt(preNext[i]))//next字符和当前字符相同，必然不会和比较的字符串当前的字符相同，故优化
                {
                    if(s.charAt(i) == s.charAt(0)) next[i] = -1;
                    else next[i] = 0;
                }
                else next[i] = preNext[i];
            }
            else next[i] = -1;
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
        BMP b = new BMP("aaaaaab");
        b.printNext();
        Scanner input = new Scanner(System.in);
        String line;
        while(!(line = input.nextLine()).equals("exit"))
        {
            System.out.println(b.contains(line));
        }
    }
}
