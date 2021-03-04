package com.hx.ssxs.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.entity.SatInfoManager;
import com.hx.ssxs.entity.SatTMInfo;

public class SatPageHandleThread implements Runnable {
  private BlockingQueue<SatTMInfo> queue = new LinkedBlockingQueue<>(
      10000);
  
  private SatInfoManager sim = null;
  
  private int mid;
  
  public SatPageHandleThread(int mid) {
    this.sim = (SatInfoManager)PageCache.simMap.get(Integer.valueOf(mid));
    this.mid = mid;
    this.sim.setSph(this);
  }
  
  public void offer(SatTMInfo listchange) {
    if (!this.queue.offer(listchange)) {
      this.queue.poll();
      this.queue.offer(listchange);
    } 
  }
  
  @Override
public void run() {
    while (true) {
      try {
        while (true) {
          SatTMInfo list = this.queue.take();
          this.sim.getPmi().setData(list, this.mid);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
}
