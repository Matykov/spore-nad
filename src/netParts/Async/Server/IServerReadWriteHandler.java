package netParts.Async.Server;

import java.nio.channels.CompletionHandler;

public interface IServerReadWriteHandler extends CompletionHandler<Integer, AttachmentServer> {
    @Override
    void completed(Integer result, AttachmentServer attach); //{
//        if (result == -1) {
//            try {
//                attach.client.close();
//                System.out.format("Stopped   listening to the   client %s%n",
//                        attach.clientAddr);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            return;
//        }
//
//        if (attach.isRead) {
//            attach.buffer.flip();
//            int limits = attach.buffer.limit();
//            byte bytes[] = new byte[limits];
//            attach.buffer.get(bytes, 0, limits);
//            Charset cs = Charset.forName("UTF-8");
//            String msg = new String(bytes, cs);
//            System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
//                    msg);
//            attach.isRead = false; // It is a write
//            attach.buffer.rewind();
//
//        } else {
//            // Write to the client
//            attach.client.write(attach.buffer, attach, this);
//            attach.isRead = true;
//            attach.buffer.clear();
//            attach.client.read(attach.buffer, attach, this);
//        }
//    }

    @Override
    void failed(Throwable e, AttachmentServer attach); //{
//        e.printStackTrace();
//    }
}