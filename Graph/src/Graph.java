import java.util.*;

public class Graph {
    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public void printGraph() {
        System.out.println(adjList);
    }
    public boolean addVertex(String vertex) {
        if (adjList.get(vertex) == null) {
            adjList.put(vertex, new ArrayList<String>());
            return true;
        }
        return false;
    }

    public boolean addEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(String vertex) {
        if (adjList.get(vertex) == null) return false;
        for (String vert : adjList.get(vertex)) {
            if (adjList.get(vert) != null) {
                adjList.get(vert).remove(vertex);
            }
        }
        adjList.remove(vertex);
        return true;
    }

    public ArrayList<String> bfsGraph(String start, HashMap<String, ArrayList<String>> adj) {
        ArrayList<String> bfs = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            String node = q.poll();
            bfs.add(node);

            for (String c: adj.get(node)) {
                if (!visited.contains(c)) {
                    visited.add(c);
                    q.add(c);
                }
            }
        }
        return bfs;
    }

    public ArrayList<String> dfsGraph(String start, HashMap<String, ArrayList<String>> adj) {
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        ArrayList<String> ls = new ArrayList<>();
        dfs(start, visited, adj, ls);
        return ls;
    }

    public void dfs(String node,HashSet<String> visited, HashMap<String, ArrayList<String>> adj, ArrayList<String> ls) {
        visited.add(node);
        ls.add(node);

        for (String c: adj.get(node)) {
            if (!visited.contains(c)) {
                dfs(c, visited, adj, ls);
            }
        }
    }

    public HashMap<String, ArrayList<String>> adjacencyList() {
        return adjList;
    }
}
