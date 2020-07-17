package solution.s04;

import java.util.LinkedList;
import java.util.List;

public class Solution04_01 {
    //解法超时-.-
    public static boolean findWhetherExistsPath1(int n, int[][] graph, int start, int target){
        int[][] subGraph = graph.clone();
        for (int i = 0; i < graph.length; i++) {
            int i0 = subGraph[i][0];
            int i1 = subGraph[i][1];
            if (graph[i][0] == start){
                if (graph[i][1] == target){
                    return true;
                }
                subGraph[i][0] = -1;
                subGraph[i][1] = -1;
                boolean flag = findWhetherExistsPath1(n, subGraph, i1, target);
                if (flag){
                    return true;
                }
            }
            subGraph[i][0] = i0;
            subGraph[i][1] = i1;
        }
        return false;
    }

    /**
     * 使用邻接表形式表示图
     * 再用DFS遍历
     */
    public static boolean findWhetherExistsPath(int n, int[][] graph, int start, int target){
        List<Integer>[] graphList = constructGraph(n, graph);
        return dfsSearch(graphList, start, target, new boolean[n]);
    }

    //使用邻接表形式表示图
    private static List<Integer>[] constructGraph(int n, int[][] graph){
        List<Integer>[] graphList = new LinkedList[n];
        for(int i = 0; i < n; i++){
            graphList[i] = new LinkedList<>();
        }
        for(int i = 0; i < graph.length; i++){
            graphList[graph[i][0]].add(graph[i][1]);
        }
        return graphList;
    }

    //深度优先遍历
    private static boolean dfsSearch(List<Integer>[] graphList, int start, int target, boolean[] visited){
        if (start == target) {
            return true;
        }else {
            visited[start] = true;  //已遍历的节点设为已访问
            for (Integer next : graphList[start]){
                if (!visited[next] && dfsSearch(graphList, next, target, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3,4}};
        boolean flag = findWhetherExistsPath1(5, graph, 3, 1);
        System.out.println(flag);

        List<Integer>[] lists = constructGraph(5, graph);
        for (List<Integer> each : lists){
            System.out.println(each);
        }

        System.out.println(findWhetherExistsPath(5, graph, 3, 1));
    }
}
