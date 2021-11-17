package com.iquantex.samples.shopping.listener;

import com.iquantex.phoenix.core.message.protobuf.Phoenix;
import com.iquantex.phoenix.eventpublish.core.CommittableEventBatchWrapper;
import com.iquantex.phoenix.eventpublish.core.EventHandler;
import com.iquantex.phoenix.server.eventstore.EventStoreRecord;
import com.iquantex.samples.shopping.coreapi.account.AccountQueryEvent;
import org.springframework.stereotype.Component;


import java.util.Iterator;
import java.util.List;

/**
 * @author quail
 */
@Component
public class PopPublishHandler implements EventHandler<Phoenix.Message, Phoenix.Message> {

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public CommittableEventBatchWrapper handleBatch(CommittableEventBatchWrapper batchWrapper) {
        List<EventStoreRecord<Phoenix.Message>> events = batchWrapper.getEvents();
        Iterator<EventStoreRecord<Phoenix.Message>> iterator = events.iterator();

        while (iterator.hasNext()) {
            EventStoreRecord<Phoenix.Message> event = iterator.next();
            Phoenix.Message content = event.getContent();
            String payloadClassName = content.getPayloadClassName();
            if (!payloadClassName.equals(AccountQueryEvent.class.getName())) {
                iterator.remove();
            }
        }
        return batchWrapper;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

