import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private static class Node{
        int x,y,num; //노드 좌표,번호
        Node left,right; //노드 왼쪽 오른쪽 자식노드
        
        public Node(int num, int x, int y){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    
    private static Node makeBT(int[][] nodeinfo){
        Node[] nodes = new Node[nodeinfo.length];
        for(int i = 0 ; i < nodeinfo.length; i++){
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        
        Arrays.sort(nodes, (o1,o2) ->{
            if(o1.y == o2.y)
                return Integer.compare(o1.x, o2.x);
            return Integer.compare(o2.y, o1.y);
        });
        
        Node root = nodes[0];
        
        for(int i = 1 ; i < nodes.length; i++){
            Node parent = root;
            while(true){
                if(nodes[i].x < parent.x){
                    if(parent.left == null){
                        parent.left = nodes[i];
                        break;
                    }
                    else{
                        parent = parent.left;
                    }
                }
                
                else{
                    if(parent.right == null){
                        parent.right = nodes[i];
                        break;
                    }
                    else{
                        parent = parent.right;
                    }
                }
            }
        }
        
        
        return nodes[0];
    }
    
    private static void preOrder(Node curr, ArrayList<Integer> answer){
        if(curr == null){
            return;
        }
        answer.add(curr.num);
        preOrder(curr.left,answer);
        preOrder(curr.right,answer);
    }
    
    private static void postOrder(Node curr,ArrayList<Integer> answer){
        if(curr ==null){
            return;
        }
        postOrder(curr.left,answer);
        postOrder(curr.right,answer);
        answer.add(curr.num);
    }
    
    
    public int[][] solution(int[][] nodeinfo) {
        Node root = makeBT(nodeinfo); // 이진트리 생성
        ArrayList<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList); //전위 순회
        ArrayList<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList); // 후위순회
        
        //결과
        int[][] answer = new int[2][nodeinfo.length];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}