import java.io.*;
import java.util.*;

public class Main{
    static class FastReader{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException{
            while(st==null||!st.hasMoreTokens())st=new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
    }
    static int find(int x,int[] p){
        while(p[x]!=x)x=p[x];
        return x;
    }
    static void union(int a,int b,int[] p){
        int ra=find(a,p),rb=find(b,p);
        if(ra!=rb)p[rb]=ra;
    }
    public static void main(String[] args)throws Exception{
        FastReader fr=new FastReader();
        int T=fr.nextInt(),n;
        StringBuilder sb=new StringBuilder();
        while(T-->0){
            n=fr.nextInt()+2;
            int[][] pt=new int[n][2];
            for(int i=0;i<n;i++){
                pt[i][0]=fr.nextInt();
                pt[i][1]=fr.nextInt();
            }
            int[] p=new int[n];
            for(int i=0;i<n;i++)p[i]=i;
            for(int i=0;i<n;i++){
                int xi=pt[i][0], yi=pt[i][1];
                for(int j=i+1;j<n;j++){
                    int dx=xi-pt[j][0]; if(dx<0)dx=-dx;
                    int dy=yi-pt[j][1]; if(dy<0)dy=-dy;
                    if(dx+dy<=1000)union(i,j,p);
                }
            }
            sb.append(find(0,p)==find(n-1,p)?"happy":"sad").append('\n');
        }
        System.out.print(sb);
    }
}
