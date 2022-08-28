public class 양과늑대 {

    static int answer = 0;
    static final int SHEEP = 0;
    static final int WOLF = 1;
    static Node[] tree;
    static boolean[][][] isVisited;

    public static int solution(int[] info, int[][] edges) {
        int N = info.length;

        tree = new Node[N];
        isVisited = new boolean[17][17][17]; // isVisited[i][j][k] = 양 j마리, 늑대 k마리 경우에 i번째 노드를 탐색했는지 여부

        for (int i = 0; i < N; i++) {
            tree[i] = new Node(i);
        }

        // 트리 정보 저장
        for (int[] edge : edges) {
            tree[edge[0]].addChild(tree[edge[1]]);
            tree[edge[1]].parent = tree[edge[0]];
        }

        // 루트부터 탐색
        search(new int[N], 0, 1, 0, info);

        return answer;
    }

    /**
     * 왼쪽, 오른쪽, 부모 노드를 탐색하는 메서드
     */
    public static void search(int[] visitCnt, int index, int sheep, int wolf, int[] info) {
        // 이미 방문한 경우의 수일 경우 탐색 안 함
        if (isVisited[index][sheep][wolf]) {
            return;
        }

        answer = Math.max(sheep, answer);

        isVisited[index][sheep][wolf] = true;
        visitCnt[index]++;

        if (tree[index].left != null) {
            goChild(visitCnt, tree[index].left.index, sheep, wolf, info);
        }

        if (tree[index].right != null) {
            goChild(visitCnt, tree[index].right.index, sheep, wolf, info);
        }

        if (tree[index].parent != null) {
            goParent(visitCnt, tree[index].parent.index, sheep, wolf, info);
        }

        isVisited[index][sheep][wolf] = false;
        visitCnt[index]--;
    }

    /**
     * 자식 노드들을 탐색하는 메서드
     * 한 번도 방문하지 않은 경우라면 sheep, wolf 개수를 바꾸어 줌
     */
    public static void goChild(int[] visitCnt, int next, int sheep, int wolf, int[] info) {
        if (visitCnt[next] == 0) {
            if (info[next] == SHEEP) {
                search(visitCnt, next, sheep + 1, wolf, info);
            } else if (info[next] == WOLF && sheep > wolf + 1) {
                search(visitCnt, next, sheep, wolf + 1, info);
            }
        }  else  {
            search(visitCnt, next, sheep, wolf, info);
        }
    }

    /**
     * 부모 노드를 탐색하는 메서드
     * 방문 여부 확인할 필요 없음
     */
    public static void goParent(int[] visitCnt, int next, int sheep, int wolf, int[] info) {
        search(visitCnt, next, sheep, wolf, info);
    }

    public static class Node {
        int index;
        Node parent;
        Node left;
        Node right;

        Node (int index) {
            this.index = index;
        }
        public void addChild(Node child) {
            if (left == null) {
                left = child;
            } else {
                right = child;
            }
        }
    }
}
