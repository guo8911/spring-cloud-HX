package com.hx.ssxs.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.data.DataPackage;
import com.hx.ssxs.entity.SatInfoManager;

public class SatOtherDataThread implements Runnable {
  private Log log = LogFactory.getLog(SatOtherDataThread.class);
  
  private BlockingQueue<DataPackage> queue = new LinkedBlockingQueue<DataPackage>(
      1000);
  
  private int mid;
  
  private SatInfoManager sim = null;
  
  public SatOtherDataThread(int mid) {
    this.mid = mid;
    this.sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(mid));
    this.sim.setSodt(this);
  }
  
  public void offData(DataPackage tmp) {
    if (!this.queue.offer(tmp)) {
      this.queue.poll();
      this.queue.offer(tmp);
    } 
  }
  
  @Override
  public void run() {
    DataPackage gp = null;
    while (true) {
      try {
        while (true) {
          gp = this.queue.take();
          if (gp != null && 
            gp.getBody().getXmlData() != null) {
            List<Map<String, String>> contain = gp.getBody()
              .getXmlData().getContain();
            this.sim.getOth()
              .handleData(gp.getHead(), this.mid, contain);
            gp = null;
          } 
        }
      } catch (Exception e) {
        if (this.log.isDebugEnabled())
          this.log.debug("处理非遥测数据发生错误", e); 
      } 
    } 
  }
}
