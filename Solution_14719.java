import java.util.Scanner;

public class Solution_14719 {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h= sc.nextInt();
        int w= sc.nextInt();
        int[] height=new int[w];
        for(int i=0;i<w;i++){
            height[i]=sc.nextInt();
        }

        //방법1) [i]기준 왼쪽, 오른쪽 최대값 중 작은 값으로 빗물 구하기 O(N^2)
        //방법1) 제일 높은 곳까지 양끝에서 현재까지의 높은 값 갱신한 넓이-블럭높이합
        int answer= trap(height);
        System.out.println(answer);
    }

    public int trap(int[] height) {
        int hidx=-1, maxh=0, total=0, sum=0;

        for(int i=0;i<height.length;i++){
            if(height[i]>maxh) {
                maxh= height[i];
                hidx=i;
            }
            sum+=height[i];
        }

        int curmax=0;
        for(int i=0;i<=hidx;i++){
            curmax= Math.max(height[i],curmax);
            total+=curmax;
        }
        curmax=0;
        for(int i=height.length-1;i>hidx;i--){
            curmax= Math.max(height[i],curmax);
            total+=curmax;
        }

        return total-sum;
    }
}
