//
//  BotaoSistema.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/10/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BotaoSistema.h"
#import "StyleKitName.h"

@implementation BotaoSistema


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    
    //// Rectangle Drawing
    UIBezierPath* rectanglePath = [UIBezierPath bezierPathWithRoundedRect: rect cornerRadius: 5];
    [StyleKitName.color setFill];
    [rectanglePath fill];
    
    
    //// Text Drawing
    CGRect textRect = CGRectMake(11, 12, 97, 13);
    NSMutableParagraphStyle* textStyle = NSMutableParagraphStyle.defaultParagraphStyle.mutableCopy;
    textStyle.alignment = NSTextAlignmentCenter;
    
    NSDictionary* textFontAttributes = @{NSFontAttributeName: [UIFont fontWithName: @"Helvetica" size: 12], NSForegroundColorAttributeName: UIColor.whiteColor, NSParagraphStyleAttributeName: textStyle};
    
    [self.titleLabel.text drawInRect: textRect withAttributes: textFontAttributes];
 
}

@end
