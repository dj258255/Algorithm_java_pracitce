import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static FastInput in = new FastInput();
    static int N, M;

    // 그래프: head, to, w, nxt
    static int[] head, to, w, nxt;
    static int ec = 0;

    // 바이너리 힙
    static long[] heap;
    static int hsz = 0;

    static void addEdge(int u, int v, int wt) {
        to[ec] = v;
        w[ec] = wt;
        nxt[ec] = head[u];
        head[u] = ec++;
    }

    static long encode(int u, int t, int type, int mode) {
        return ((long) t << 32) | ((long) u << 2) | ((long) type << 1) | mode;
    }
    static int topU(long x)    { return (int)((x >>> 2) & 0x3FFFFFFF); }
    static int topT(long x)    { return (int)(x >>> 32);           }
    static int topType(long x) { return (int)((x >>> 1) & 1);      }
    static int topMode(long x) { return (int)(x & 1);               }

    static void push(long x) {
        heap[++hsz] = x;
        int i = hsz;
        while (i > 1 && heap[i] < heap[i >> 1]) {
            long tmp = heap[i]; heap[i] = heap[i >> 1]; heap[i >> 1] = tmp;
            i >>= 1;
        }
    }

    static long pop() {
        long ret = heap[1], last = heap[hsz--];
        int i = 1, c;
        while ((c = i << 1) <= hsz) {
            if (c < hsz && heap[c + 1] < heap[c]) c++;
            if (last <= heap[c]) break;
            heap[i] = heap[c];
            i = c;
        }
        heap[i] = last;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        N = in.readInt();
        M = in.readInt();

        head = new int[N + 1];
        Arrays.fill(head, -1);
        to  = new int[2 * M + 5];
        w   = new int[2 * M + 5];
        nxt = new int[2 * M + 5];
        // ← 여기만 바뀌었습니다. 힙 크기를 M에 비례하도록 충분히 크게 잡습니다.
        heap = new long[(M * 4) + 5];

        for (int i = 0; i < M; i++) {
            int a = in.readInt(), b = in.readInt(), d = in.readInt() * 2;
            addEdge(a, b, d);
            addEdge(b, a, d);
        }

        int[] distF = new int[N + 1];
        int[][] distW = new int[N + 1][2];
        Arrays.fill(distF, INF);
        for (int i = 1; i <= N; i++) {
            distW[i][0] = distW[i][1] = INF;
        }

        distF[1] = 0;
        distW[1][0] = 0;
        push(encode(1, 0, 0, 0));
        push(encode(1, 0, 1, 0));

        while (hsz > 0) {
            long cur = pop();
            int u    = topU(cur);
            int t    = topT(cur);
            int type = topType(cur);
            int mode = topMode(cur);

            if (type == 0) {
                if (t > distF[u]) continue;
                for (int e = head[u]; e != -1; e = nxt[e]) {
                    int v = to[e], nt = t + w[e];
                    if (nt < distF[v]) {
                        distF[v] = nt;
                        push(encode(v, nt, 0, 0));
                    }
                }
            } else {
                if (t > distW[u][mode]) continue;
                for (int e = head[u]; e != -1; e = nxt[e]) {
                    int v = to[e], nm = 1 - mode;
                    int cost = (mode == 0 ? w[e] >> 1 : w[e] << 1);
                    int nt = t + cost;
                    if (nt < distW[v][nm]) {
                        distW[v][nm] = nt;
                        push(encode(v, nt, 1, nm));
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int wolfBest = Math.min(distW[i][0], distW[i][1]);
            if (distF[i] < wolfBest) answer++;
        }
        System.out.println(answer);
    }

    static class FastInput {
        final DataInputStream in = new DataInputStream(System.in);
        final byte[] buf = new byte[1 << 16];
        int ptr = 0, len = 0;

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                if (len < 0) return -1;
                ptr = 0;
            }
            return buf[ptr++];
        }

        int readInt() throws IOException {
            int c, s = 1, x = 0;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            if (c == '-') { s = -1; c = read(); }
            for (; c >= '0' && c <= '9'; c = read()) {
                x = x * 10 + (c - '0');
            }
            return x * s;
        }
    }
}
