package action.qnaAction;

import java.util.List;

import vo.Q_replyBean;

public class QnaReplyListAction {
	
	private int messageTotalCount;
	private List<Q_replyBean> messageList;

	public QnaReplyListAction(List<Q_replyBean> messageList, int messageTotalCount) {
		this.messageList = messageList;
		this.messageTotalCount = messageTotalCount;
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public List<Q_replyBean> getMessageList() {
		return messageList;
	}

	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
}
