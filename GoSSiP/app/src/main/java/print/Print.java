package print;

import android.content.Context;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class Print {

    public void print(Context ctx,Object o)
    {
        Toast.makeText(ctx,""+o,Toast.LENGTH_SHORT).show();
    }
    public void printl(Context ctx,Object o)
    {
        Toast.makeText(ctx,""+o,Toast.LENGTH_LONG).show();
    }
    public void sprintf(Context ctx,Object o) //s->success,p-> print ,f->fancy
    {
        FancyToast.makeText(ctx,""+o,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
    }
    public void fprintf(Context ctx,Object o) //s->success,p-> print ,f->fancy
    {
        FancyToast.makeText(ctx,""+o,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
    }
    public void sprintfl(Context ctx,Object o) //s->success,p-> print ,f->fancy,l->long
    {
        FancyToast.makeText(ctx,""+o,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
    }
    public void fprintfl(Context ctx,Object o) //s->success,p-> print ,f->fancy,l->long
    {
        FancyToast.makeText(ctx,""+o,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
    }
}
