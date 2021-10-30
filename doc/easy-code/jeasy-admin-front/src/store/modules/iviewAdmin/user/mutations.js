import { setToken } from '@/libs/util'

export default {
  setAvatar (state, avatarPath) {
    state.avatarImgPath = avatarPath
  },
  setUserId (state, id) {
    state.userId = id
  },
  setUserName (state, name) {
    state.userName = name
  },
  setAccess (state, access) {
    state.access = access
  },
  setToken (state, token) {
    state.token = token
    setToken(token)
  },
  setHasGetInfo (state, status) {
    state.hasGetInfo = status
  },
  setMessageCount (state, count) {
    state.unreadCount = count
  },
  setMessageUnreadList (state, list) {
    state.messageUnreadList = list
  },
  setMessageReadedList (state, list) {
    state.messageReadedList = list
  },
  setMessageTrashList (state, list) {
    state.messageTrashList = list
  },
  updateMessageContentStore (state, { msg_id, content }) {
    state.messageContentStore[msg_id] = content
  },
  moveMsg (state, { from, to, msg_id }) {
    const index = state[from].findIndex(_ => _.msg_id === msg_id)
    const msgItem = state[from].splice(index, 1)[0]
    msgItem.loading = false
    state[to].unshift(msgItem)
  }
}
