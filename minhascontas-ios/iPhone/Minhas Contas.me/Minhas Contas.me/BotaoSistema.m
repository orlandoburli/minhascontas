//
//  BotaoSistema.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/10/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BotaoSistema.h"
#import "StyleKitName.h"
#import <QuartzCore/QuartzCore.h>

@implementation BotaoSistema

- (void)drawRect:(CGRect)rect {
    
    UIBezierPath* rectanglePath = [UIBezierPath bezierPathWithRoundedRect: rect cornerRadius: 5];
    [StyleKitName.color setFill];
    [rectanglePath fill];
    
    UIColor *color = self.currentTitleColor;
    self.titleLabel.layer.shadowColor = [color CGColor];
    self.titleLabel.layer.shadowRadius = 4.0f;
    self.titleLabel.layer.shadowOpacity = .9;
    self.titleLabel.layer.shadowOffset = CGSizeZero;
    self.titleLabel.layer.masksToBounds = NO;

}

@end
