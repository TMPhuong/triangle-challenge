package model;

import message.MessageService;

public enum TriangleTypeBySideLength {
    EQUILATERAL("triangle.type.equilateral.description"),
    ISOSCELES("triangle.type.isosceles.description"),
    SCALENE("triangle.type.scalene.description");

    private final String messageKey;

    private TriangleTypeBySideLength(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getDescription(MessageService messageService) {
        return messageService.getString(this.messageKey);
    }
}
