//
//  BaseViewController.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BaseViewController : UIViewController <UITextFieldDelegate>

-(void) dismissAlert:(UIAlertView *) alertView;

-(void) timedAlert: (UIAlertView *) alert;

-(void) showTimedAlert: (NSString *)mensagem withTitulo: (NSString *) titulo;

@end
