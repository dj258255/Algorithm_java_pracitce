import java.util.*;

class Solution {
    public class SegmentTree {
        int arrLength; //요소 길이
        int tree[]; // 구간합 트리
        public SegmentTree(int arr[]) {
            this.arrLength = arr.length;
            this.tree = new int[arr.length*4];
            init(0, arr.length-1, 1, arr);
        }
        public int init(int start, int end, int node, int arr[]) {
            if(start == end) { /* 리프노드이거나 자식노드들이 구간합이 모두구해졌을 경우 */
                return tree[node] = arr[start]; /* 구간합 트리에 넣어준다 */
            }
            /* 반씩 나눠서  재귀적으로 자식노드들의 구간합을 구해준다 */
            int mid = (start+end)/2;
            return tree[node] = init(start, mid, node*2, arr) + init(mid+1, end, node*2+1, arr);
        }
        public int sum(int start, int end, int node, int left, int right) {
            if(left>end || right < start) {
                return 0;
            }
            if(left <=start && end <=right) {
                return tree[node];
            }
            /* 필요한 구간마다 밑에서부터 구간합을 가지고 올라온다 */
            int mid = (start+end)/2;
            return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
        }

        public int update(int start, int end, int node, int idx, int value) {
            if(idx < start || end < idx) {
                return tree[node];
            }
            if(start == end && start == idx) {
                return tree[node] = value;
            }

            int mid = (start + end)/2;
            return tree[node] = update(start, mid, node*2, idx, value) + update(mid+1, end, node*2+1, idx, value);
        }

    }

    public int[] solution(String[] infos, String[] querys) {
        List<Integer> answer = new ArrayList<>();
        int[][][][][] personNum = new int[3][2][2][2][100001];
        for(String info : infos){
            String[] str = info.split(" ");
            int[] lan = getLanguage(str[0]);
            int[] job = getJob(str[1]);
            int[] level = getLevel(str[2]);
            int[] food = getFood(str[3]);
            int score = Integer.valueOf(str[4]);
            for(int l : lan){
                for(int j : job){
                    for(int le : level){
                        for(int f : food){
                            personNum[l][j][le][f][score]++;
                            // System.out.printf("[%d][%d][%d][%d][%d]에  추가\n", l,j,le,f,score);
                        }
                    }
                }
            }
        }
        SegmentTree[][][][] trees = new SegmentTree[3][2][2][2];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++) {
                    for(int l = 0; l < 2; l++){
                        trees[i][j][k][l] = new SegmentTree(personNum[i][j][k][l]);
                    }
                }
            }
        }
        for(String query : querys){
            String[] str = query.split(" and ");
            int[] lan = getLanguage(str[0]);
            int[] job = getJob(str[1]);
            int[] level = getLevel(str[2]);
            int[] food = getFood(str[3].split(" ")[0]);
            int score = Integer.valueOf(str[3].split(" ")[1]);

            int sum = 0;
            for(int l : lan){
                for(int j : job){
                    for(int le : level){
                        for(int f : food){
                            sum += trees[l][j][le][f].sum(0,100000, 1, score, 100000);
                        }
                    }
                }
            }
            answer.add(sum);
        }

        int[] ret = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            ret[i] = answer.get(i);
        }

        return ret;
    }

    int[] getLanguage(String lan){
        if(lan.equals("cpp")){
            return new int[]{0};
        }
        if(lan.equals("java")){
            return new int[]{1};
        }
        if(lan.equals("python")){
            return new int[]{2};
        }
        return new int[]{0,1,2};
    }

    int[] getJob(String job){
        if(job.equals("backend")){
            return new int[]{0};
        }
        if(job.equals("frontend")){
            return new int[]{1};
        }
        return new int[]{0,1};
    }

    int[] getLevel(String level){
        if(level.equals("junior")){
            return new int[]{0};
        }
        if(level.equals("senior")){
            return new int[]{1};
        }
        return new int[]{0,1};
    }

    int[] getFood(String food){
        if(food.equals("chicken")){
            return new int[]{0};
        }
        if(food.equals("pizza")){
            return new int[]{1};
        }
        return new int[]{0,1};
    }
}
