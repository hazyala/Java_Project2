package w0827;

import java.util.Calendar;
import java.util.Random;

public class Chapter09_LAB1 {
    public static void main(String[] args) {

        String[] Say = {
                "삶이 있는 한 희망은 있다. - 키케로",
                "산다는 것은 곧 고통을 감내하는 것이다. - 칼릴 지브란",
                "피할 수 없다면 즐겨라. - 로버트 엘리엇",
                "먼저 핀 꽃은 먼저 진다. - 한상남",
                "행복은 습관이다, 그것을 몸에 지니라. - 허버트",
                "꿈을 계속 간직하고 있으면 반드시 실현될 때가 온다. - 괴테",
                "태양을 바라보라, 그러면 그늘을 볼 수 없다. - 헬렌 켈러",
                "인생에서 가장 큰 영광은 넘어지지 않는 것에 있는 것이 아니라, 넘어질 때마다 일어서는 것에 있다. - 넬슨 만델라",
                "행동이 항상 행복을 가져다주지는 않지만, 행동 없이는 행복도 없다. - 벤자민 디즈라엘리",
                "미래는 현재 우리가 무엇을 하는가에 달려 있다. - 마하트마 간디",
                "우리가 이룬 것만큼, 이루지 못한 것도 우리 자신이다. - 윌리엄 셰익스피어",
                "성공은 최종적인 것이 아니며, 실패는 치명적인 것이 아니다. 중요한 것은 계속하겠다는 용기다. - 윈스턴 처칠",
                "인생은 용기 있는 모험이거나 아무것도 아니다. - 헬렌 켈러",
                "승리는 가장 끈기 있는 자에게 돌아간다. - 나폴레옹 보나파르트",
                "절대 포기하지 말라, 위대한 일은 시간이 걸린다. - 앤드류 매튜스",
                "자신이 될 수 있는 최고의 자신이 되라. - 랄프 왈도 에머슨",
                "우리가 이룬 것만큼, 이루지 못한 것도 우리 자신이다. - 윌리엄 셰익스피어"
        };

        Random rand = new Random();
        int today_index = rand.nextInt(Say.length);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("-------------------------------------------------------------");
        System.out.println("         < 오늘의 명언 >");
        System.out.println("오늘은 " + year + "년 " + month + "월 " + day + "일 입니다.");
        System.out.println();
        System.out.println("오늘의 명언: " + Say[today_index]);
        System.out.println("-------------------------------------------------------------");
    }
}