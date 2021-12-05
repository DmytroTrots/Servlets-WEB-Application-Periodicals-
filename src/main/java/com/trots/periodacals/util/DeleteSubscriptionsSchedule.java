package com.trots.periodacals.util;

import com.trots.periodacals.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class DeleteSubscriptionsSchedule extends TimerTask {

    private static final Logger log = LogManager.getLogger(DeleteSubscriptionsSchedule.class);

    @Override
    public void run() {
        ReceiptService.getInstance().deleteOrdersAfterTime();
        log.trace("Delete subscriptions called");
    }
}
