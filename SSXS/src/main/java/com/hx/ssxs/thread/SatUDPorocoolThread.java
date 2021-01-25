package com.hx.ssxs.thread;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.cache.PageCache;
import com.hx.ssxs.data.DataPackage;
import com.hx.ssxs.data.GroupParameter;
import com.hx.ssxs.entity.SatInfoManager;

public class SatUDPorocoolThread implements Runnable {
  private Log log = LogFactory.getLog(SatUDPorocoolThread.class);
  
  private BlockingQueue<DataPackage> queue = new LinkedBlockingQueue<>(1000);
  
  private Integer mid;
  
  private SatInfoManager sim = null;
  
  public void offData(DataPackage tmp) {
    if (!this.queue.offer(tmp)) {
      this.queue.poll();
      this.queue.offer(tmp);
    } 
  }
  
  public SatUDPorocoolThread(int mid) {
    this.mid = Integer.valueOf(mid);
    this.sim = (SatInfoManager)PageCache.map.get(Integer.valueOf(mid));
    this.sim.setProcoolDataThread(this);
  }
  
  @Override
public void run() {
    DataPackage gp = null;
    GroupParameter grouparam = null;
    while (true) {
      try {
        while (true) {
          gp = this.queue.take();
          if (gp != null) {
            int bid = gp.getHead().getMesSign();
            int tasksign = gp.getHead().getTaskSign();
            if (this.log.isDebugEnabled()) {
				this.log.debug(String.valueOf(Thread.currentThread().getName()) + ":mid=" + this.mid + " tasksign=" + tasksign);
			} 
            if (bid == 65536) {
				this.sim.getTmSource().handle(gp);
			} 
            if (bid == 65793) {
              List<GroupParameter> groupParameters = gp.getBody().getRealTimeData().getGroupParameters();
              if (this.log.isDebugEnabled()) {
				this.log.debug("参数组数：" + groupParameters.size());
			} 
              for (int i = 0; i < groupParameters.size(); i++) {
                grouparam = groupParameters.get(i);
                String time = grouparam.getDataTimeString();
                if (grouparam != null) {
                  if (this.log.isDebugEnabled()) {
					this.log.debug("[组里参数为空！]");
				} 
                  this.sim.getTmf().checking(grouparam, time, this.mid.intValue());
                } 
              } 
            } 
            if (bid == 65794) {
            	List list = gp.getBody().getDelayData().getPackageParameters(); 
            }
            break;
          } 
        } 
        grouparam = null;
        gp = null;
      } catch (Exception e) {
        if (this.log.isDebugEnabled()) {
			this.log.debug("处理数据发生错误", e);
		} 
      } 
    } 
  }
}
