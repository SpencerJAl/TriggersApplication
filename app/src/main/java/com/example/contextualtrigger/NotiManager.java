package com.example.contextualtrigger;


import java.time.LocalDateTime;

public class NotiManager {

    private LocalDateTime lastNotificationTime;
    private LocalDateTime nowNotificationTime;
    private int timePassed = 0;
    private int timeDelay = 2;
    private boolean initialNotification = true;

    public NotiManager() {


    }

    public void sendNotification(String triggerName) {
        getCurrentTime();
        if(!initialNotification) {
            calcTimeDifference();
        }
        if(timePassed < timeDelay & !initialNotification){ //if the time that has passed isnt as much as the delay then sleep.
            System.out.println(timePassed);
            System.out.println(timeDelay);
            //need to find a way to add delay between noti's
            //Time Delay - Time Passed = time to actual delay.


            //Comment from Alex- Could you not setup a second timer that count's down, run a if check and if time passed < time delay then you could go ahead and run it?
            //Or am I misunderstanding the issue?
        }

        if (triggerName.equals("1")) {
            displayNoti1();
        } else if (triggerName.equals("2")) {
            displayNoti2();
        } else if (triggerName.equals("3")) {
            displayNoti3();
        } else if (triggerName.equals("4")) {
            displayNoti4();
        } else if (triggerName.equals("5")) {
            displayNoti5();
        }

        initialNotification = false;

    }

    private void displayNoti1() {
        System.out.println("Noti 1");
    }

    private void displayNoti2() {
        System.out.println("Noti 2");
    }

    private void displayNoti3() {
        System.out.println("Noti 3");
    }

    private void displayNoti4() {
        System.out.println("Noti 4");
    }

    private void displayNoti5() {
        System.out.println("Noti 5");
    }


    private void getCurrentTime() {
        if(initialNotification) {
            lastNotificationTime = LocalDateTime.now();
        } else{
            nowNotificationTime = LocalDateTime.now();
        }
    }

    private void calcTimeDifference(){
        if(initialNotification){
            timePassed = timeDelay+1;
        }else {
            timePassed = (int) java.time.Duration.between(lastNotificationTime, nowNotificationTime).toMinutes();
        }
    }
}
