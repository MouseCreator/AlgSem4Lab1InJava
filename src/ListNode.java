/**
 * Вузол у зв'язнову стеку або черзі
 *
 * @param next  наступний вузол
 * @param value значення, що зберігається у вузлі
 */
public record ListNode(Hashable value, ListNode next) { }
