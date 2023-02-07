import React, {useCallback, useEffect, useState} from "react";
import { GoogleLogin } from "react-google-login";
import { gapi } from "gapi-script";

const clientId = "300713055142-t1lskcvgiqdn5fln1ajsiast5t3kdm28.apps.googleusercontent.com";

const GoogleButton = ({onSocial}) => {

    const [token, setToken] = useState("");

    useEffect(()=>{
        function start() {
            gapi.client.init({
                clientId,
                scope: 'email',
            });
        }

        gapi.load('client:auth2', start);
    }, []);

    const onSuccess = (response) => {
        console.log(response.accessToken);
        setToken(response.accessToken);
    };

    const onFailure = (response)=> {
        console.log(response);
    };

    return (
        <div>
          
                <GoogleLogin
                    clientId={clientId}
                    buttonText="구글로 로그인하기"
                    onSuccess={onSuccess}
                    onFailure={onFailure}/>
        
            
        </div>
    );
};

export default GoogleButton;
