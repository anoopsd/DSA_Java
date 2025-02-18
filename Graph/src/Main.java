import java.util.*;

public class Main {
    // Function to add an edge (undirected graph)
    private static void addEdge(ArrayList<ArrayList<Integer>> matrix, int src, int dest) {
        matrix.get(src).set(dest, 1);
        matrix.get(dest).set(src, 1); // Undirected graph
    }
    public static void main(String[] args) {
        Graph myGraph = new Graph();
        myGraph.addVertex("A");
        myGraph.addVertex("B");
        myGraph.addVertex("C");
        myGraph.addVertex("D");
        myGraph.addVertex("E");
        myGraph.addVertex("F");
        myGraph.addVertex("G");
        myGraph.addEdge("A","B");
        myGraph.addEdge("A","C");
        myGraph.addEdge("B","D");
        myGraph.addEdge("B","E");
        myGraph.addEdge("A","B");
        myGraph.addEdge("C","F");
        myGraph.addEdge("C","G");
        System.out.println(myGraph.dfsGraph("A", myGraph.adjacencyList()));
        myGraph.printGraph();

        // Define adjacency matrix as an ArrayList of ArrayLists
        ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();
        int size = 6; // Total number of nodes
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(size, 0)); // Initialize with 0s
            adjMatrix.add(row);
        }

        // Adding edges to form three separate provinces
        // Province 1: Nodes {0, 1, 2}
        addEdge(adjMatrix, 0, 1);
        addEdge(adjMatrix, 1, 2);
        addEdge(adjMatrix, 2, 0);

        // Province 2: Nodes {3, 4}
        addEdge(adjMatrix, 3, 4);

        // Province 3: Node {5} (Isolated node)
        System.out.println(numberOfProvinces(adjMatrix, 6));

        char[][] grid = {
                {'1', '1', '0', '0', '0', '1', '1'},
                {'1', '1', '0', '0', '0', '1', '1'},
                {'0', '0', '0', '1', '0', '0', '0'},
                {'0', '0', '0', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0', '1', '1'}
        };
        System.out.println(numberOfIslands(grid));

        int[][] flood = {
                {1,1,1},
                {2,2,0},
                {2,2,2}
        };
        for (int[] row : floodFilldfs(flood, 2,0,3 )) {
            for (int ch : row) {
                System.out.print(ch + "  "); // Print each character with spacing
            }
            System.out.println(); // Move to the next line after printing a row
        }


        int[][] rotten = {
                {0,1,2},
                {0,1,1},
                {2,1,1}
        };
        System.out.println(rottenOranges(rotten));


    }

    public static int numberOfProvinces(ArrayList<ArrayList<Integer>> adj, int v) {
        HashMap<Integer , ArrayList<Integer>> adjList = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < v; i++) {
            adjList.put(i, new ArrayList<Integer>());
        }
        //Convert adjacency matrix to list
        for (int i = 0; i < v; i++) {
            for (int j = 0; j< v; j++) {
                if (adj.get(i).get(j) == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);

                }
            }
        }

        int count = 0;
        for (int i=0; i < v;i++) {
            if (!visited.contains(i)) {
                count++;
                dfsInteger(i, visited, adjList);
            }
        }
        return count;
    }
    public static void dfsInteger(int node, HashSet<Integer> visited, HashMap<Integer, ArrayList<Integer>> adjList) {
        visited.add(node);
        for (Integer c: adjList.get(node)) {
            if (!visited.contains(c)) {
                dfsInteger(c, visited, adjList);
            }
        }
    }
    // Number of islands problem in Graph.
    public static int numberOfIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && grid[i][j] == '1') {
                    count++;
                    bfsInteger(i, j, visited, grid);
                }
            }
        }
        return count;
    }

    public static void bfsInteger(int row, int col, int[][] visited, char[][] grid) {
        visited[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row,col));
        int n = grid.length;
        int m = grid[0].length;

        while(!q.isEmpty()) {
            int ro = q.peek().first;
            int co = q.peek().second;
            q.remove();

            for (int delrow = -1; delrow <= 1; delrow++) {
                for (int delcol = -1; delcol <= 1; delcol++) {
                    int nrow = ro + delrow;
                    int ncol = co + delcol;
                    if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && visited[nrow][ncol] == 0 && grid[nrow][ncol] == '1') {
                        visited[nrow][ncol] = 1;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }

    public static char[][] floodFill(char[][] grid, int sr, int sc, char newColor) {
        int n = grid.length;
        int m = grid[0].length;
        char initialColor = grid[sr][sc];
        int[][] visited = new int[n][m];
        bfsFill(sr, sc, grid, visited, initialColor, newColor);
        return grid;
    }

    public static void bfsFill(int sr, int sc, char[][] grid, int[][] visited,char initColor, char newColor) {
        visited[sr][sc] = 1;
        grid[sr][sc] = newColor;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sr,sc));
        int n = grid.length;
        int m = grid[0].length;

        while(!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();

            int[] dRow = {-1,1,0,0};
            int[] dCol = {0,0,-1,1};

            for (int i = 0; i < 4; i++) {
                int nRow = row + dRow[i];
                int nCol = col + dCol[i];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && visited[nRow][nCol] == 0 && grid[nRow][nCol] == initColor) {
                    visited[nRow][nCol] = 1;
                    grid[nRow][nCol] = newColor;
                    q.add(new Pair(nRow, nCol));
                }
            }
        }
    }

    public static int[][] floodFilldfs(int[][] grid, int sr, int sc, int newColor) {
        int initColor = grid[sr][sc];
        int[][] result = grid.clone();
        int[] dRow = {-1,1,0,0};
        int[] dCol = {0,0,-1,1};
        int n = result.length;
        int m = result[0].length;
        dfsFill(result, sr, sc, initColor, newColor, dRow, dCol,n,m);
        return result;
    }

    public static void dfsFill(int[][] res, int sr, int sc, int initColor, int newColor, int[] dRow, int[] dCol, int n, int m) {
        res[sr][sc] = newColor;
        for(int i =0; i< 4; i++) {
            int nRow = sr + dRow[i];
            int nCol = sc + dCol[i];

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && res[nRow][nCol] == initColor)  {
                dfsFill(res, nRow,nCol,initColor, newColor, dRow, dCol, n,m);
            }
        }
    }

    public static int rottenOranges(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        Queue<OrangePair> q = new LinkedList<>();
        int cntTime = 0;
        int cntFresh = 0;
        int[] dRow = {-1,1,0,0};
        int[] dCol = {0,0,-1,1};

        for (int i= 0 ;i < n; i++) {
            for(int j =0; j < m; j++) {
                if (grid[i][j] == 2){
                    q.add(new OrangePair(i,j,0));
                    visited[i][j] = 2;
                } else {
                    visited[i][j] = 0;
                }
                if(grid[i][j] == 1) cntFresh++;
            }
        }

        while(!q.isEmpty()) {
            int delRow = q.peek().first;
            int delCol = q.peek().second;
            int delTime = q.peek().time;
            cntTime = Math.max(cntTime, delTime);
            q.remove();

            for(int i = 0; i < 4; i ++) {
                int nRow = delRow + dRow[i];
                int nCol = delCol + dCol[i];
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && visited[nRow][nCol] == 0 && grid[nRow][nCol] == 1) {
                    q.add(new OrangePair(nRow, nCol, delTime + 1));
                    visited[nRow][nCol] = 2;
                    cntFresh--;
                }
            }

        }
        if(cntFresh != 0) return -1;
        return cntTime;
    }
}