import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int p;
	static int m;
	static int[] l;
	static String[] n;

	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = new int[p];
		n = new String[p];
		for(int i = 0 ; i < p; i++) {
			st = new StringTokenizer(br.readLine().trim());
			l[i] = Integer.parseInt(st.nextToken());
			n[i] = st.nextToken();
		}
	}

	public static void solve() throws IOException{
        List<Integer> baseLevel = new ArrayList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        List<Boolean> started = new ArrayList<>();

        for(int i = 0 ; i < p; i++) {
        	boolean placed = false;
        	for(int r = 0; r < rooms.size(); r++) {
        		if(started.get(r)) continue;
        		if(Math.abs(baseLevel.get(r) - l[i]) <= 10) {
        			rooms.get(r).add(i);
        			if(rooms.get(r).size() == m) {
        				started.set(r,true);
        			}
        			placed = true;
        			break;
        		}
        	}
        	if(!placed) {
        		baseLevel.add(l[i]);
        		List<Integer> newRoom = new ArrayList<>();
        		newRoom.add(i);
        		rooms.add(newRoom);
        		started.add(m==1);
        	}
        }
        for (int r = 0; r < rooms.size(); r++) {
            if (started.get(r)) bw.write("Started!\n");
            else bw.write("Waiting!\n");

            List<Integer> room = rooms.get(r);
            room.sort((a, b) -> n[a].compareTo(n[b]));
            for (int idx : room) {
                bw.write(l[idx] + " " + n[idx] + "\n");
            }
        }
	}

	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
}